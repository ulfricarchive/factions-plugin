package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.invitation.Invitation;

public class InvitationToCreationFunction extends InvitationFunction {

	public InvitationToCreationFunction() {
		super("creation");
	}

	@Override
	public Object apply(Invitation invitation) {
		return invitation.getCreation();
	}

}
