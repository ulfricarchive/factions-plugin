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
		if (!isTargetInSameFaction()) {
			tell("factions-kick-not-member");
			return;
		}

		if (isSelfKick()) {
			tell("factions-kick-self");
			return;
		}

		if (!isKickPermitted()) {
			tell("factions-kick-rank-too-low");
			return;
		}

		removeTargetFromFaction();
		removeFactionFromTarget();

		notifyInvolvedParties();
	}

	private boolean isTargetInSameFaction() {
		return faction.getIdentifier().equals(target.getFaction());
	}

	private boolean isSelfKick() {
		return target.getIdentifier().equals(denizen.getIdentifier());
	}

	private boolean isKickPermitted() {
		int senderLevel = permissionLevel();
		int targetLevel = calculatePermissionLevel(target);
		return senderLevel > targetLevel;
	}

	private void removeTargetFromFaction() {
		Map<UUID, Set<String>> roles = faction.getMembersToRoles();
		if (roles != null) {
			roles.remove(UniqueIdHelper.parseUniqueIdExact(target.getIdentifier()));
			faction.setMembersToRoles(roles);
			factions.persistFaction(faction);
		}
	}

	private void removeFactionFromTarget() {
		target.setFaction(null);
		factions.persistDenizen(target);
	}

	private void notifyInvolvedParties() {
		tellDenizensExceptForSender("factions-kick-by");
		tell("factions-kick");
		tellTarget("factions-kick-to");
	}

}
