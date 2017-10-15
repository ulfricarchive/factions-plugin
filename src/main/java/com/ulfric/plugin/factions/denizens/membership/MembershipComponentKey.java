package com.ulfric.plugin.factions.denizens.membership;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/membership")
enum MembershipComponentKey implements ComponentKey<MembershipComponent> {

	INSTANCE;

	@Override
	public Class<MembershipComponent> getComponentType() {
		return MembershipComponent.class;
	}

	@Override
	public String toString() {
		return getName();
	}

}
