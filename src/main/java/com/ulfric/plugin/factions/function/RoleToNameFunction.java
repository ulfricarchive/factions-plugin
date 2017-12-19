package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.role.Role;

public class RoleToNameFunction extends Function<Role> {

	public RoleToNameFunction() {
		super("name", Role.class);
	}

	@Override
	public Object apply(Role role) {
		return role.getName();
	}

}
