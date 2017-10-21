package com.ulfric.plugin.factions.factions.system;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/system")
enum SystemComponentKey implements ComponentKey<SystemComponent> {

	INSTANCE;

	@Override
	public Class<SystemComponent> getComponentType() {
		return SystemComponent.class;
	}

}
