package com.ulfric.plugin.factions.factions.invitations.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.invitations.Invitation;

public class InvitationInviterFunction extends Function<Invitation> {

	public InvitationInviterFunction() {
		super("inviter", Invitation.class);
	}

	@Override
	public Object apply(Invitation invitation) {
		return invitation.getInviter();
	}

}
