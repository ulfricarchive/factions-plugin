package com.ulfric.plugin.factions.function;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.plugin.factions.model.invitation.Invitation;

public class InvitationToInviterFunction extends InvitationFunction {

	public InvitationToInviterFunction() {
		super("inviter");
	}

	@Override
	public Object apply(Invitation invitation) {
		return PlayerHelper.getName(invitation.getInviter());
	}

}
