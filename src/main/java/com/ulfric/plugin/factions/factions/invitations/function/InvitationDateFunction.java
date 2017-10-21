package com.ulfric.plugin.factions.factions.invitations.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.invitations.Invitation;

public class InvitationDateFunction extends Function<Invitation> {

	public InvitationDateFunction() {
		super("date", Invitation.class);
	}

	@Override
	public Object apply(Invitation invitation) {
		return invitation.getCreated();
	}

}
