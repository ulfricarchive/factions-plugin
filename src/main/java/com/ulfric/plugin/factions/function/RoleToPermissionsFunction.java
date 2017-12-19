package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.role.Role;

public class RoleToPermissionsFunction extends Function<Role> {

	public RoleToPermissionsFunction() {
		super("permissions", Role.class);
	}

	@Override
	public Object apply(Role role) {
		return role.getPermissions();
	}

}
