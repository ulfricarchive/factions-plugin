package com.ulfric.plugin.factions.entity.role;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ulfric.commons.collection.Collectors2;

public enum StandardRoles implements Permissible {

	LEADER;

	private static final Map<String, StandardRoles> NAME_TO_ROLE = new HashMap<>();

	public static Map<String, StandardRoles> getAsMap() {
		return Collections.unmodifiableMap(NAME_TO_ROLE);
	}

	private final Set<String> permissions;

	StandardRoles(String... permissions) {
		this.permissions = Arrays.stream(permissions).collect(Collectors2.toUnmodifiableSet());
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	@Override
	public Set<String> getPermissions() {
		return permissions;
	}

}
