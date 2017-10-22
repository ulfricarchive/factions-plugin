package com.ulfric.plugin.factions.command;

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

@Name("kick")
@Asynchronous
@FactionsPermission("kick")
public class FactionsKickCommand extends DenizenFactionTargetFactionsCommand {

	private Membership membership;

	@Override
	public CompletableFuture<?> runAsFaction() {
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

		return saveFaction().thenAccept(response -> {
			if (!ResponseHelper.changedData(response)) {
				throw new FactionSaveException("Failed to save kick", response);
			}

			tellFaction("factions-kicked");
		})
		.thenCompose(ignore -> {
			MembershipComponent membershipComponent = target.removeComponent(MembershipComponent.KEY);
			if (membershipComponent == null) {
				return FutureHelper.empty();
			}

			return Factions.saveDenizen(target).thenAccept(membershipResponse -> {
				if (!ResponseHelper.changedData(membershipResponse)) {
					throw new FactionSaveException("Failed to save kick for member", membershipResponse);
				}

				// TODO error handling?
				Factions.tellDenizen(target, "factions-kicked-from", details());
			});
		});
	}

}
