package com.ulfric.plugin.factions.function;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.plugin.factions.entity.invitation.Invitation;

public class InvitationInviterFunction extends InvitationFunction {

	public InvitationInviterFunction() {
		super("inviter");
	}

	@Override
	public Object apply(Invitation invitation) {
		return PlayerHelper.getName(invitation.getInviter());
	}

}
