package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.invitation.Invitation;

public abstract class InvitationFunction extends Function<Invitation> {

	public InvitationFunction(String name) {
		super(name, Invitation.class);
	}

}
