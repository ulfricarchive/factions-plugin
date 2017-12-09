package com.ulfric.plugin.factions.function;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.plugin.factions.model.invitation.Invitation;

public class InvitationInvitedFunction extends InvitationFunction {

	public InvitationInvitedFunction() {
		super("invited");
	}

	@Override
	public Object apply(Invitation invitation) {
		return PlayerHelper.getName(invitation.getInvited());
	}

}
