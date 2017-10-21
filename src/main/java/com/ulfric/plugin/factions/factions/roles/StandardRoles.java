package com.ulfric.plugin.factions.factions.roles;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public enum StandardRoles implements Permissible {

	FOUNDER,
	LEADER(10000) {
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

	private final int worth;
	private final Set<String> permissions;

	StandardRoles() {
		this(0);
	}

	StandardRoles(int worth) {
		this.worth = worth;
		this.permissions = Collections.emptySet();
	}

	StandardRoles(int worth, String... permissions) {
		this.worth = worth;
		this.permissions = Arrays.stream(permissions)
				.map(String::toLowerCase)
				.collect(Collectors.toSet());
	}

	public final String getName() {
		return name().toLowerCase();
	}

	@Override
	public final int getWorth() {
		return worth;
	}

	@Override
	public boolean hasPermission(String node) {
		return permissions.contains(node.toLowerCase());
	}

}
