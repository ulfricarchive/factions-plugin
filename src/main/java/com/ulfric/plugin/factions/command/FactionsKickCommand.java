package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.UniqueIdHelper;

@Name("kick")
@FactionPermission("kick")
public class FactionsKickCommand extends TargetDenizenFactionFactionsCommand {

	@Override
	public void run() {
		if (!faction.getIdentifier().equals(target.getFaction())) {
			tell("factions-kick-not-member");
			return;
		}

		if (target.getIdentifier().equals(denizen.getIdentifier())) {
			tell("factions-kick-self");
			return;
		}

		int senderLevel = permissionLevel();
		int targetLevel = calculatePermissionLevel(target);
		if (targetLevel >= senderLevel) {
			tell("factions-kick-rank-too-low");
			return;
		}

		Map<UUID, Set<String>> roles = faction.getMembersToRoles();
		if (roles != null) {
			roles.remove(UniqueIdHelper.parseUniqueIdExact(target.getIdentifier()));
			faction.setMembersToRoles(roles);
			factions.persistFaction(faction);
		}

		target.setFaction(null);
		factions.persistDenizen(target);

		tellDenizensExceptForSender("factions-kick-by");
		tell("factions-kick");
		tellTarget("factions-kick-to");
	}

}
