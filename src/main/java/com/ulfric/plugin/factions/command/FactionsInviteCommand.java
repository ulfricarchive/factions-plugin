package com.ulfric.plugin.factions.command;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.factions.invitations.Invitation;
import com.ulfric.plugin.factions.factions.invitations.InvitationsComponent;

@Name("invite")
@Alias("inv")
@Asynchronous
@FactionsPermission("invite")
public class FactionsInviteCommand extends DenizenFactionTargetFactionsCommand {

	private Invitation invitation;

	@Override
	public CompletableFuture<?> runAsFaction() {
		InvitationsComponent invitationsComponent = faction.getComponent(InvitationsComponent.KEY);

		if (invitationsComponent == null) {
			invitationsComponent = new InvitationsComponent();
			faction.addComponent(invitationsComponent);
		}

		Map<UUID, Invitation> invitations = invitationsComponent.getInvitations();
		if (invitations == null) {
			invitations = new HashMap<>();
			invitationsComponent.setInvitations(invitations);
		} else {
			invitation = invitations.get(targetUniqueId);
			if (invitation != null) {
				tell("factions-invite-already-invited");
				return null;
			}
		}

		invitation = new Invitation();
		invitation.setInvited(targetUniqueId);
		invitation.setInviter(uniqueId());
		invitation.setCreated(TemporalHelper.instantNow());
		invitations.put(targetUniqueId, invitation);
		invitationsComponent.setInvitations(invitations);

		return saveFaction().thenAccept(response -> {
			if (!ResponseHelper.changedData(response)) {
				throw new FactionSaveException("Failed to save invites", response);
			}

			tellFaction("factions-invited");
			Factions.tellDenizen(target, "factions-invited-to", details());
		});
	}

}
