package com.ulfric.plugin.factions.factions.roles;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/roles")
enum RolesComponentKey implements ComponentKey<RolesComponent> {

	INSTANCE;

	@Override
	public Class<RolesComponent> getComponentType() {
		return RolesComponent.class;
	}

}
