package com.ulfric.plugin.factions.function;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.plugin.factions.model.invitation.Invitation;

public class InvitationCreationFunction extends InvitationFunction {

	public InvitationCreationFunction() {
		super("creation");
	}

	@Override
	public Object apply(Invitation invitation) {
		return PlayerHelper.getName(invitation.getInviter());
	}

}
