package com.ulfric.plugin.factions.factions.claims;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/claims")
enum ClaimsComponentKey implements ComponentKey<ClaimsComponent> {

	INSTANCE;

	@Override
	public Class<ClaimsComponent> getComponentType() {
		return ClaimsComponent.class;
	}

}
