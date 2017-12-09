package com.ulfric.plugin.factions.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.invitation.Invitation;

public class FactionInvitesFunction extends FactionFunction {

	public FactionInvitesFunction() {
		super("invites");
	}

	@Override
	public Object apply(Faction faction) {
		List<Invitation> invitations = faction.getInvitations();

		if (invitations == null) {
			return null;
		}

		invitations = new ArrayList<>(invitations);
		Collections.sort(invitations, (o1, o2) -> o1.getCreation().compareTo(o2.getCreation()));
		return invitations;
	}

}
