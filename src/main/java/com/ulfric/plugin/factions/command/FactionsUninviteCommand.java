package com.ulfric.plugin.factions.command;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.model.invitation.Invitation;

@Name("uninvite")
@Alias({ "uninv", "deinv", "deinvite" })
@FactionPermission("uninvite")
public class FactionsUninviteCommand extends TargetDenizenFactionFactionsCommand {

	protected Invitation invite;

	@Override
	public void run() {
		List<Invitation> invites = faction.getInvitations();
		if (CollectionUtils.isEmpty(invites)) {
			tell("factions-uninvite-no-invites");
			return;
		}

		UUID targetUniqueId = targetUniqueId();

		Iterator<Invitation> invitesIterator = invites.iterator();
		while (invitesIterator.hasNext()) {
			Invitation invite = invitesIterator.next();
			if (Objects.equals(invite.getInvited(), targetUniqueId)) {
				invitesIterator.remove();
				faction.setInvitations(invites);
				factions.persistFaction(faction);

				tell("factions-uninvite");
				tellDenizensExceptForSender("factions-uninvite-by");
				tellTarget("factions-uninvite-to");
				this.invite = invite;
				return;
			}
		}

		tell("factions-uninvite-not-invited");
	}

}
