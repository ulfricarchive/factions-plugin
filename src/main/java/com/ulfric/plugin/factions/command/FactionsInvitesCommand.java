package com.ulfric.plugin.factions.command;

import java.util.concurrent.Future;

import com.ulfric.commons.naming.Name;

@Name("invites")
@FactionsPermission("invite-list")
public class FactionsInvitesCommand extends DenizenFactionFactionsCommand {

	@Override
	public Future<?> runAsFaction() {
		tell("factions-invitation-list");
		return null;
	}

}
