package com.ulfric.plugin.factions.factions.roles;

import java.util.List;

import com.ulfric.commons.value.Bean;

public class Role extends Bean {

	private String name;
	private String title;
	private List<String> parents;
	private List<String> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getParents() {
		return parents;
	}

	public void setParents(List<String> parents) {
		this.parents = parents;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

}
