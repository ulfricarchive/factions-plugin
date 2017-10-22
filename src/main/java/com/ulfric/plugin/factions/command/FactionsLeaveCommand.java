package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Membership;

@Name("leave")
@Asynchronous
public class FactionsLeaveCommand extends DenizenFactionFactionsCommand {

	private Membership membership;

	@Override
	public Future<?> runAsFaction() {
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

		return saveFaction().whenComplete((response, error) -> {
			if (error != null || !ResponseHelper.changedData(response)) {
				internalError("factions-leave-save-error", error);
				return;
			}

			tellFaction("factions-leave");

			MembershipComponent membershipComponent = denizen.removeComponent(MembershipComponent.KEY);
			if (membershipComponent == null) {
				return;
			}

			Factions.saveDenizen(denizen).whenComplete((membershipResponse, membershipError) -> {
				// TODO error handling?
				tell("factions-left");
			});
		});
	}

}
