package com.ulfric.plugin.factions.factions.members;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/members")
enum MembersComponentKey implements ComponentKey<MembersComponent> {

	INSTANCE;

	@Override
	public Class<MembersComponent> getComponentType() {
		return MembersComponent.class;
	}

}
