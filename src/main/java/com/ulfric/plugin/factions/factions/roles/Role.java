package com.ulfric.plugin.factions.factions.roles;

import java.util.Set;

import com.ulfric.commons.value.Bean;

public class Role extends Bean implements Permissible {

	private String title;
	private Set<String> parents;
	private Set<String> permissions;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getParents() {
		return parents;
	}

	public void setParents(Set<String> parents) {
		this.parents = parents;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean hasPermission(String permission) {
		Set<String> permissions = getPermissions();

		if (permissions == null) {
			return false;
		}

		return permissions.contains(permission.toLowerCase());
	}

}
