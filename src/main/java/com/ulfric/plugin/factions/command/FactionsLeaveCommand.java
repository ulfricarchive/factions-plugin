package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.factions.entity.role.StandardRoles;

@Name("leave")
public class FactionsLeaveCommand extends FactionFactionsCommand {

	@Override
	public void run() {
		if (isLeader()) {
			tell("factions-leave-leader-must-disband");
			return;
		}

		Map<UUID, Set<String>> members = faction.getMembersToRoles();
		members.remove(uniqueId());
		faction.setMembersToRoles(members);

		denizen.setFaction(null);

		factions.persistDenizen(denizen);
		factions.persistFaction(faction);

		tell("factions-leave");
		tellDenizensExceptForSender("factions-leave-by");
	}

	private boolean isLeader() {
		Map<UUID, Set<String>> members = faction.getMembersToRoles();

		if (MapUtils.isEmpty(members)) {
			return false;
		}

		Set<String> roles = members.get(uniqueId());

		if (members.size() == 1) {
			return roles != null;
		}

		return roles.contains(StandardRoles.LEADER.getName());
	}

}
