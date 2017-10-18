package com.ulfric.plugin.factions.factions.description;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/description")
enum DescriptionComponentKey implements ComponentKey<DescriptionComponent> {

	INSTANCE;

	@Override
	public Class<DescriptionComponent> getComponentType() {
		return DescriptionComponent.class;
	}

}
