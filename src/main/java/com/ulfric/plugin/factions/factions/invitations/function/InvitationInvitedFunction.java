package com.ulfric.plugin.factions.factions.invitations.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.invitations.Invitation;

public class InvitationInvitedFunction extends Function<Invitation> {

	public InvitationInvitedFunction() {
		super("invited", Invitation.class);
	}

	@Override
	public Object apply(Invitation invitation) {
		return invitation.getInvited();
	}

}
