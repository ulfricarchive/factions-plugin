package com.ulfric.plugin.factions.factions.roles;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public enum StandardRoles implements Permissible {

	FOUNDER,
	LEADER {
		@Override
		public boolean hasPermission(String node) {
			return true;
		}
	};

	public static StandardRoles getByName(String name) {
		try {
			return valueOf(name.toUpperCase());
		} catch (IllegalArgumentException doesNotExist) {
			return null;
		}
	}

	private final Set<String> permissions;

	StandardRoles() {
		this.permissions = Collections.emptySet();
	}

	StandardRoles(String... permissions) {
		this.permissions = Arrays.stream(permissions)
				.map(String::toLowerCase)
				.collect(Collectors.toSet());
	}

	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public boolean hasPermission(String node) {
		return permissions.contains(node.toLowerCase());
	}

}
