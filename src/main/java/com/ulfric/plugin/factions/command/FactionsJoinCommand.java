package com.ulfric.plugin.factions.command;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.invitation.Invitation;
import com.ulfric.plugin.factions.model.role.StandardRoles;

@Name("join")
@Alias("j")
public class FactionsJoinCommand extends TargetFactionFactionsCommand {

	private Invitation invite;

	@Override
	public void run() {
		Faction current = EntityHelper.getFactionOf(denizen);

		if (current != null) {
			tell("factions-join-already-in-faction");
			return;
		}

		Map<UUID, Set<String>> members = target.getMembersToRoles();
		int currentMemberCount = MapHelper.size(members);
		if (currentMemberCount >= factions.settings().maxPlayersPerFaction()) {
			tell("factions-join-full");
			return;
		}

		resolveInvitation();
		if (invite == null) {
			if (!BooleanUtils.isTrue(target.getOpen())) {
				tell("factions-join-not-invited");
				return;
			}

			tell("factions-join-open");
			tellTarget("factions-join-open-by-user");
		} else {
			tell("factions-join-invited");
			tellTarget("factions-join-by-user");

			List<Invitation> invites = target.getInvitations();
			invites.remove(invite);
		}

		Set<String> roles = new HashSet<>();
		roles.add(StandardRoles.MEMBER.getName());
		members.put(uniqueId(), roles);
		target.setMembersToRoles(members);

		denizen.setFaction(target.getIdentifier());

		factions.persistFaction(target);
		factions.persistDenizen(denizen);
	}

	private void resolveInvitation() {
		List<Invitation> invitations = target.getInvitations();

		if (CollectionUtils.isEmpty(invitations)) {
			return;
		}

		UUID uniqueId = uniqueId();
		for (Invitation invite : invitations) {
			if (Objects.equals(invite.getInvited(), uniqueId)) {
				this.invite = invite;
				return;
			}
		}
	}

}
