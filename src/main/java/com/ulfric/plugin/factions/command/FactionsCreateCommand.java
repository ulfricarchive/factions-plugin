package com.ulfric.plugin.factions.command;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.entity.Denizen;
import com.ulfric.plugin.factions.entity.Faction;
import com.ulfric.plugin.factions.entity.role.StandardRoles;

@Name("create")
@Alias("new")
public class FactionsCreateCommand extends FactionsCommand {

	@Argument(optional = true)
	private String name;

	private Faction faction;

	@Override
	public void run() {
		if (senderIsAlreadyInFaction()) {
			tell("factions-create-already-in-faction");
			return;
		}

		if (name == null) {
			requestNameOnSign();
			return;
		}

		String error = getNameErrorIfPresent();
		if (error != null) {
			tell(error);
			return;
		}

		createFaction();
	}

	private boolean senderIsAlreadyInFaction() {
		return factions.getDenizen(uniqueId()).getFaction() != null;
	}

	private void requestNameOnSign() {
		requestOnSign("factions-create-sign-give-name", "f create");
	}

	private void createFaction() {
		faction = factions.getFaction(name);

		if (BooleanUtils.isTrue(faction.getPermanent())) {
			tell("factions-create-name-taken-permanent");
			return;
		}

		Map<UUID, Set<String>> members = faction.getMembersToRoles();
		if (MapUtils.isNotEmpty(members)) {
			tell("factions-create-name-taken");
			return;
		}

		members = new HashMap<>();
		Set<String> roles = new HashSet<>();
		roles.add(StandardRoles.LEADER.getName());
		members.put(uniqueId(), roles);
		faction.setMembersToRoles(members);

		faction.setCreation(Instant.now());

		factions.persistFaction(faction);

		Denizen denizen = factions.getDenizen(player());
		denizen.setFaction(name);
		factions.persistDenizen(denizen);

		tell("factions-create");
	}

	private String getNameErrorIfPresent() {
		if (nameIsBelowRequiredLength()) {
			return "factions-create-below-required-length";
		}

		if (nameIsAboveRequiredLength()) {
			return "factions-create-above-required-length";
		}

		if (!StringUtils.isAlpha(name)) {
			return "factions-create-must-be-alphabetical";
		}

		if (isNameProhibited()) {
			return "factions-create-name-prohibited";
		}

		return null;
	}

	private boolean nameIsBelowRequiredLength() { // TODO configurable
		return name.length() < 2;
	}

	private boolean nameIsAboveRequiredLength() { // TODO configurable
		return name.length() > 8;
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
