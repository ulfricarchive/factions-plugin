package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Membership;

@Name("invite")
@Alias("inv")
@Asynchronous
@FactionsPermission("invite")
public class FactionsKickCommand extends DenizenFactionTargetFactionsCommand {

	private Membership membership;

	@Override
	public Future<?> runAsFaction() {
		MembersComponent membersComponent = faction.getComponent(MembersComponent.KEY);

		if (membersComponent == null) {
			tell("factions-kick-no-members");
			return null;
		}

		Map<UUID, Membership> memberships = membersComponent.getMembers();
		if (memberships == null) {
			tell("factions-kick-no-members");
			return null;
		}

		membership = memberships.get(targetUniqueId);
		if (membership == null) {
			tell("factions-kick-member-not-found");
			return null;
		}

		int senderWorth = MembersComponent.getPermissions(faction, uniqueId()).getWorth();
		int targetWorth = MembersComponent.getPermissions(faction, targetUniqueId).getWorth();

		if (targetWorth > senderWorth) {
			tell("factions-kick-higher-rank");
			Factions.tellDenizen(target, "factions-kick-attempt"); // TODO put this in their inbox
			return null;
		}

		memberships.remove(targetUniqueId);

		return saveFaction().whenComplete((response, error) -> {
			if (error != null || !ResponseHelper.changedData(response)) {
				internalError("factions-kick-save-error", error);
				return;
			}

			tellFaction("factions-kicked");

			MembershipComponent membershipComponent = target.removeComponent(MembershipComponent.KEY);
			if (membershipComponent == null) {
				return;
			}

			Factions.saveDenizen(target).whenComplete((membershipResponse, membershipError) -> {
				// TODO error handling?
				Factions.tellDenizen(target, "factions-kicked-from", details());
			});
		});
	}

}
