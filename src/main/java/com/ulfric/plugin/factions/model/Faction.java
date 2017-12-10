package com.ulfric.plugin.factions.model;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ulfric.plugin.factions.model.dtr.DTR;
import com.ulfric.plugin.factions.model.invitation.Invitation;
import com.ulfric.plugin.factions.model.role.Role;

public class Faction extends Entity {

	private String description;
	private Instant creation;
	private Map<UUID, Set<String>> membersToRoles;
	private Map<String, Role> roles;
	private Boolean permanent;
	private Boolean open;
	private List<Invitation> invitations;
	private DTR deathsTillRaidable;

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

	public List<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

	public DTR getDeathsTillRaidable() {
		return deathsTillRaidable;
	}

	public void setDeathsTillRaidable(DTR deathsTillRaidable) {
		this.deathsTillRaidable = deathsTillRaidable;
	}

}