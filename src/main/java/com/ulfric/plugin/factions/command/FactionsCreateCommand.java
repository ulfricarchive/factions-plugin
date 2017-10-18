package com.ulfric.plugin.factions.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

import com.google.common.util.concurrent.Futures;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.RegexHelper;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.name.NameComponent;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Position;
import com.ulfric.plugin.factions.factions.roles.StandardRoles;
import com.ulfric.plugin.locale.TellService;

@Name("create")
@Alias({"make", "new"})
// TODO cooldown - Limit of 10 per 10 minutes
public class FactionsCreateCommand extends DenizenFactionsCommand { // TODO cleanup, integrate with Rules / Control

	private static final Pattern VALID_NAME = RegexHelper.compile("[a-zA-Z0-9]+"); // TODO configurable

	@Argument(optional = true)
	private String name;

	@Override
	public void run(Context context) {
		if (name == null) {
			// TODO sign for the user to enter a name
			return;
		}

		if (name.equals("?")) {
			// TODO randomly generate a name
			return;
		}

		String error = getSyntacticError();
		if (error != null) {
			TellService.sendMessage(context.getSender(), error);
			return;
		}

		super.run(context);
	}

	@Override
	public Future<?> runAsDenizen(Context context, Entity denizen) {
		Player player = Context.getPlayer(context);

		MembershipComponent membership = membership(denizen);
		if (StringUtils.isNotEmpty(membership.getFaction())) {
			TellService.sendMessage(player, "factions-create-already-member");
			return null;
		}

		return Factions.getFaction(name).whenComplete((faction, createError) -> {
			if (createError != null || faction == null) {
				TellService.sendMessage(player, "factions-create-failed");
				return;
			}

			if (faction.hasComponent(MembersComponent.KEY)) {
				TellService.sendMessage(player, "factions-create-already-exists");
				return;
			}
			faction.addComponent(createMembers(player.getUniqueId()));

			faction.addComponent(formalName());

			membership.setFactionByEntity(faction);

			Details details = Details.of("faction", faction);
			Future<?> factionWait = Factions.saveFaction(faction)
					.thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionCreateException("Failed to save faction", response);
						}
					}).thenApply(ignore -> {
						Future<Response> denizenWait = Factions.saveDenizen(denizen);
						return Futures.getUnchecked(denizenWait);
					}).thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionCreateException("Failed to save denizen", response);
						}
					})
					.thenRun(() -> TellService.sendMessage(player, "factions-create", details))
					.exceptionally(exception -> {
						exception.printStackTrace(); // TODO error handling
						details.add("error", exception);
						TellService.sendMessage(player, "factions-error-save", details);
						return null;
					});
			Futures.getUnchecked(factionWait);
		});
	}

	private NameComponent formalName() {
		NameComponent formalName = new NameComponent();
		formalName.setName(name);
		return formalName;
	}

	private MembersComponent createMembers(UUID leader) {
		MembersComponent members = new MembersComponent();
		Map<UUID, List<Position>> membersToPositions = new HashMap<>();
		List<Position> positions = new ArrayList<>();
		positions.add(position(StandardRoles.FOUNDER));
		positions.add(position(StandardRoles.LEADER));
		membersToPositions.put(leader, positions);
		members.setMembers(membersToPositions);
		return members;
	}

	private Position position(StandardRoles role) {
		Position position = new Position();
		position.setRole(role.getName());
		return position;
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
