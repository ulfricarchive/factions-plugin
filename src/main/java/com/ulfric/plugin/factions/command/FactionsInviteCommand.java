package com.ulfric.plugin.factions.command;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.entity.invitation.Invitation;

@Name("invite")
@Alias("inv")
@FactionPermission("invite")
public class FactionsInviteCommand extends TargetDenizenFactionFactionsCommand {

	private Invitation invitation;

	@Override
	public void run() {
		if (faction.getIdentifier().equals(target.getFaction())) {
			tell("factions-invite-already-member");
			return;
		}

		UUID targetUniqueId = UniqueIdHelper.parseUniqueIdExact(target.getIdentifier());

		List<Invitation> invitations = faction.getInvitations();
		if (invitations != null) {
			for (Invitation existing : invitations) {
				if (Objects.equals(targetUniqueId, existing.getInvited())) {
					invitation = existing;
					tell("factions-invite-already-invited");
					return;
				}
			}
		} else {
			invitations = new ArrayList<>();
			faction.setInvitations(invitations);
		}

		invitation = new Invitation();
		invitation.setInvited(targetUniqueId);
		invitation.setInviter(uniqueId());
		invitation.setCreation(Instant.now());

		invitations.add(invitation);

		tellDenizensExceptForSender("factions-invite-by");
		tell("factions-invite");
		tellTarget("factions-invite-to");
	}

}
