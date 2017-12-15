package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ulfric.commons.value.UniqueIdHelper;

public abstract class SkeletalFactionsKickCommand extends TargetDenizenFactionFactionsCommand {

	protected void removeLink() {
		removeTargetFromFaction();
		removeFactionFromTarget();
	}

	private void removeTargetFromFaction() {
		Map<UUID, Set<String>> roles = faction.getMembersToRoles();
		if (roles != null) {
			roles.remove(UniqueIdHelper.parseUniqueIdExact(target.getIdentifier()));
			faction.setMembersToRoles(roles);
		}
	}

	private void removeFactionFromTarget() {
		target.setFaction(null);
	}

	protected void persistTargetAndFaction() {
		factions.persistFaction(faction);
		factions.persistDenizen(target);
	}

}
