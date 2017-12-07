package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;

@Name("invites")
@Alias("invitations")
@FactionPermission("invite-list")
public class FactionsInvitesCommand extends FactionFactionsCommand {

	@Override
	public void run() {
		tell("factions-invite-list");
	}

}
