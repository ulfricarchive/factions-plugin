package com.ulfric.plugin.factions.command;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.dragoon.rethink.Location;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.argument.Denizen;
import com.ulfric.plugin.factions.factions.invitations.Invitation;
import com.ulfric.plugin.factions.factions.invitations.InvitationsComponent;

@Name("invite")
@Alias("inv")
@Asynchronous
@FactionsPermission("invite")
public class FactionsInviteCommand extends DenizenFactionFactionsCommand {

	@Denizen
	@Argument
	private Entity invite;

	private Invitation invitation;

	private UUID invitedUniqueId;

	@Override
	public Future<?> runAsDenizen() {
		if (denizen.equals(invite)) {
			tell("factions-invite-cannot-be-self");
			return null;
		}

		resolveUniqueIdOfInvite();
		if (invitedUniqueId == null) {
			tell("factions-invite-could-not-find-denizen-id");
			return null;
		}

		return super.runAsDenizen();
	}

	@Override
	public Future<?> runAsFaction() {
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
			invitation = invitations.get(invitedUniqueId);
			if (invitation != null) {
				tell("factions-invite-already-invited");
				return null;
			}
		}

		invitation = new Invitation();
		invitation.setInvited(invitedUniqueId);
		invitation.setInviter(uniqueId());
		invitation.setCreated(TemporalHelper.instantNow());
		invitations.put(invitedUniqueId, invitation);
		invitationsComponent.setInvitations(invitations);

		return saveFaction().whenComplete((response, error) -> {
			if (error != null || !ResponseHelper.changedData(response)) {
				internalError("factions-invite-save-error", error);
				return;
			}

			tellFaction("factions-invited");
			Factions.tellDenizen(invite, "factions-invited-to", details());
		});
	}

	private void resolveUniqueIdOfInvite() {
		Location location = invite.getLocation();
		if (location == null) {
			return;
		}

		String key = location.getKey();
		if (StringUtils.isEmpty(key)) {
			return;
		}

		invitedUniqueId = UniqueIdHelper.parseUniqueId(key);
	}

}
