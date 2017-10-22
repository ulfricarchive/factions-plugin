package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.naming.Name;
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

@Name("uninvite")
@Alias({"uninv", "deinv", "deinvite"})
@Asynchronous
@FactionsPermission("deinvite")
public class FactionsUninviteCommand extends DenizenFactionFactionsCommand {

	@Denizen
	@Argument
	private Entity invite;

	private Invitation invitation;

	private UUID deinvitedUniqueId;

	@Override
	public Future<?> runAsDenizen() {
		if (denizen.equals(invite)) {
			tell("factions-deinvite-cannot-be-self");
			return null;
		}

		resolveUniqueIdOfInvite();
		if (deinvitedUniqueId == null) {
			tell("factions-deinvite-could-not-find-denizen-id");
			return null;
		}

		return super.runAsDenizen();
	}

	@Override
	public Future<?> runAsFaction() {
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

		invitation = invitations.remove(deinvitedUniqueId);
		if (invitation == null) {
			tell("factions-deinvite-not-invited");
			return null;
		}

		invitationsComponent.setInvitations(invitations);

		return saveFaction().whenComplete((response, error) -> {
			if (error != null || !ResponseHelper.changedData(response)) {
				internalError("factions-deinvite-save-error", error);
				return;
			}

			tellFaction("factions-deinvited");
			Factions.tellDenizen(invite, "factions-deinvited-to", details());
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

		deinvitedUniqueId = UniqueIdHelper.parseUniqueId(key);
	}

}
