package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.factions.invitations.Invitation;
import com.ulfric.plugin.factions.factions.invitations.InvitationsComponent;

@Name("uninvite")
@Alias({"uninv", "deinv", "deinvite"})
@Asynchronous
@FactionsPermission("deinvite")
public class FactionsUninviteCommand extends DenizenFactionTargetFactionsCommand {

	private Invitation invitation;

	@Override
	public CompletableFuture<?> runAsFaction() {
		InvitationsComponent invitationsComponent = faction.getComponent(InvitationsComponent.KEY);

		if (invitationsComponent == null) {
			tell("factions-deinvite-no-invitations");
			return null;
		}

		Map<UUID, Invitation> invitations = invitationsComponent.getInvitations();
		if (invitations == null) {
			tell("factions-deinvite-no-invitations");
			return null;
		}

		invitation = invitations.remove(targetUniqueId);
		if (invitation == null) {
			tell("factions-deinvite-not-invited");
			return null;
		}

		invitationsComponent.setInvitations(invitations);

		return saveFaction().thenAccept(response -> {
			if (!ResponseHelper.changedData(response)) {
				throw new FactionSaveException("Failed to save deinvite", response);
			}

			tellFaction("factions-deinvited");
			Factions.tellDenizen(target, "factions-deinvited-to", details());
		});
	}

}
