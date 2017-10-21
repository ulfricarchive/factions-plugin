package com.ulfric.plugin.factions.factions.invitations;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/invitations")
enum InvitationsComponentKey implements ComponentKey<InvitationsComponent> {

	INSTANCE;

	@Override
	public Class<InvitationsComponent> getComponentType() {
		return InvitationsComponent.class;
	}

}
