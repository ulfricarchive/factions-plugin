package com.ulfric.plugin.factions.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.RegexHelper;
import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.name.NameComponent;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Membership;
import com.ulfric.plugin.factions.factions.roles.StandardRoles;

@Name("create")
@Alias({"make", "new"})
// TODO cooldown - Limit of 10 per 10 minutes
public class FactionsCreateCommand extends DenizenFactionsCommand { // TODO cleanup, integrate with Rules / Control

	private static final Pattern VALID_NAME = RegexHelper.compile("[a-zA-Z0-9]+"); // TODO configurable

	@Argument(optional = true)
	protected String name;

	protected Entity faction;

	@Override
	public void run() {
		if (name == null) {
			requestOnSign("factions-create-sign", "/f create");
			return;
		}

		if (name.equals("?")) {
			// TODO randomly generate a name
			return;
		}

		String error = getSyntacticError();
		if (error != null) {
			tell(error);
			return;
		}

		super.run();
	}

	@Override
	public CompletableFuture<?> runAsDenizen() {
		MembershipComponent membership = membership(denizen);
		if (StringUtils.isNotEmpty(membership.getFaction())) {
			tell("factions-create-already-member");
			return null;
		}

		return Factions.getFaction(name).thenCompose(faction -> {
			if (faction == null) {
				tell("factions-create-failed");
				return FutureHelper.empty();
			}

			this.faction = faction;

			Details details = details();
			details.add("faction", faction);

			if (faction.hasComponent(MembersComponent.KEY)) {
				tell("factions-create-already-exists", details);
				return FutureHelper.empty();
			}

			faction.addComponent(createMembers(uniqueId()));
			faction.addComponent(formalName());

			membership.setFactionByEntity(faction);

			return Factions.saveFaction(faction)
					.thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionSaveException("Failed to save faction", response);
						}
					})
					.thenCompose(ignore -> Factions.saveDenizen(denizen))
					.thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionSaveException("Failed to save denizen", response);
						}
					})
					.thenRun(() -> tell("factions-create", details));
		});
	}

	protected NameComponent formalName() {
		NameComponent formalName = new NameComponent();
		formalName.setName(name);
		return formalName;
	}

	private MembersComponent createMembers(UUID leader) {
		MembersComponent members = new MembersComponent();
		Map<UUID, Membership> membersToPositions = new HashMap<>();

		Membership membership = new Membership();
		membership.setJoined(TemporalHelper.instantNow());
		membership.setUniqueId(leader);

		List<String> roles = new ArrayList<>();
		roles.add(StandardRoles.FOUNDER.getName());
		roles.add(StandardRoles.LEADER.getName());
		membership.setRoles(roles);

		membersToPositions.put(leader, membership);

		members.setMembers(membersToPositions);

		return members;
	}

	private MembershipComponent membership(Entity denizen) {
		MembershipComponent membership = denizen.getComponent(MembershipComponent.KEY);

		if (membership == null) {
			membership = new MembershipComponent();
			denizen.addComponent(membership);
		}

		return membership;
	}

	private String getSyntacticError() {
		if (!isNameWithinRequiredLength()) {
			return "factions-create-name-length";
		}

		if (isNameProhibited()) {
			return "factions-create-name-prohibited";
		}

		if (!RegexHelper.matches(name, VALID_NAME)) {
			return "factions-create-name-invalid-characters";
		}

		return null;
	}

	private boolean isNameWithinRequiredLength() { // TODO configurable
		int length = name.length();
		return length >= 2 && length <= 10;
	}

	private boolean isNameProhibited() { // TODO configurable
		String name = this.name.toLowerCase();
		return name.equals("null") ||
				name.equals("nul") ||
				name.equals("system") ||
				name.equals("staff") ||
				name.equals("admin");
	}

}
