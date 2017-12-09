package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.role.Role;

public class RolePermissionsFunction extends Function<Role> {

	public RolePermissionsFunction() {
		super("permissions", Role.class);
	}

	@Override
	public Object apply(Role role) {
		return role.getPermissions();
	}

}
