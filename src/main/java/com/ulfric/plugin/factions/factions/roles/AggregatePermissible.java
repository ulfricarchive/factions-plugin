package com.ulfric.plugin.factions.factions.roles;

import java.util.List;

public final class AggregatePermissible implements Permissible {

	private final List<Permissible> permissions;

	public AggregatePermissible(List<Permissible> permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean hasPermission(String node) {
		for (Permissible permissible : permissions) {
			if (permissible.hasPermission(node)) {
				return true;
			}
		}

		return false;
	}

}
