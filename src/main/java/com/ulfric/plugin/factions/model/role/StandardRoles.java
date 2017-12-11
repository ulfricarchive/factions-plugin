package com.ulfric.plugin.factions.model.role;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ulfric.commons.collection.Collectors2;

public enum StandardRoles implements Permissible {

	LEADER("disband", "role-list", "description-set", "invite"),
	MEMBER();

	private static final Map<String, StandardRoles> NAME_TO_ROLE = new HashMap<>();

	static {
		Arrays.stream(values())
			.forEach(value -> NAME_TO_ROLE.put(value.getName(), value));
	}

	public static Map<String, StandardRoles> getAsMap() {
		return Collections.unmodifiableMap(NAME_TO_ROLE);
	}

	public static StandardRoles getByName(String name) {
		return NAME_TO_ROLE.get(name);
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
