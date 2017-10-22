package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Membership;

@Name("leave")
@Asynchronous
public class FactionsLeaveCommand extends DenizenFactionFactionsCommand {

	private Membership membership;

	@Override
	public CompletableFuture<?> runAsFaction() {
		MembersComponent membersComponent = faction.getComponent(MembersComponent.KEY);

		if (membersComponent == null) {
			tell("factions-leave-no-members");
			return null;
		}

		Map<UUID, Membership> memberships = membersComponent.getMembers();
		if (memberships == null) {
			tell("factions-leave-no-members");
			return null;
		}

		if (memberships.size() == 1) {
			tell("factions-leave-only-member");
			return null;
		}

		List<UUID> leaders = MembersComponent.getLeaders(faction);
		if (leaders.size() == 1 && leaders.contains(uniqueId())) {
			tell("factions-only-leader");
			return null;
		}

		membership = memberships.remove(uniqueId());
		if (membership == null) {
			tell("factions-leave-member-not-found");
			return null;
		}

		membersComponent.setMembers(memberships);

		return saveFaction().thenAccept(response -> {
			if (!ResponseHelper.changedData(response)) {
				throw new FactionSaveException("Failed to save leave", response);
			}

			tellFaction("factions-leave");
		}).thenCompose(ignore -> {
			MembershipComponent membershipComponent = denizen.removeComponent(MembershipComponent.KEY);
			if (membershipComponent == null) {
				return FutureHelper.empty();
			}

			return Factions.saveDenizen(denizen).thenAccept(membershipResponse -> {
				if (!ResponseHelper.changedData(membershipResponse)) {
					throw new FactionSaveException("Failed to save leave on client", membershipResponse);
				}

				tell("factions-left");
			});
		});
	}

}
