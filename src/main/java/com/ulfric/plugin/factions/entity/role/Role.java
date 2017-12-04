package com.ulfric.plugin.factions.entity.role;

import java.util.Set;

import com.ulfric.commons.value.Bean;

public class Role extends Bean implements Permissible {

	private String name;
	private Set<String> permissions;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

}
