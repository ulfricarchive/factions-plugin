package com.ulfric.plugin.factions.factions.invitations.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.factions.invitations.InvitationsComponent;

public class InvitationsFunction extends Function<Entity> {

	public InvitationsFunction() {
		super("invitations", Entity.class);
	}

	@Override
	public Object apply(Entity entity) {
		InvitationsComponent invitations = entity.getComponent(InvitationsComponent.KEY);
		if (invitations == null) {
			return null;
		}

		return invitations.getSortedInvitations();
	}

}
