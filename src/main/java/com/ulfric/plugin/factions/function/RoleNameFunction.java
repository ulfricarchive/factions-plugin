package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.entity.role.Role;

public class RoleNameFunction extends Function<Role> {

	public RoleNameFunction() {
		super("name", Role.class);
	}

	@Override
	public Object apply(Role role) {
		return role.getName();
	}

}
