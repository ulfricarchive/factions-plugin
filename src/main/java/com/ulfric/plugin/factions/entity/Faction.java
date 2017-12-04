package com.ulfric.plugin.factions.entity;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ulfric.plugin.factions.entity.role.Role;

public class Faction extends Entity {

	private String description;
	private Instant creation;
	private Map<UUID, Set<String>> membersToRoles;
	private Map<String, Role> roles;
	private Boolean permanent;
	private Boolean open;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreation() {
		return creation;
	}

	public void setCreation(Instant creation) {
		this.creation = creation;
	}

	public Map<UUID, Set<String>> getMembersToRoles() {
		return membersToRoles;
	}

	public void setMembersToRoles(Map<UUID, Set<String>> membersToRoles) {
		this.membersToRoles = membersToRoles;
	}

	public Map<String, Role> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Role> roles) {
		this.roles = roles;
	}

	public Boolean getPermanent() {
		return permanent;
	}

	public void setPermanent(Boolean permanent) {
		this.permanent = permanent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

}
