package com.ulfric.plugin.factions.factions.roles;

public enum EmptyPermissible implements Permissible {

	INSTANCE;

	@Override
	public boolean hasPermission(String node) {
		return false;
	}

}
