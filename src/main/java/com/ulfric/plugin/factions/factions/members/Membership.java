package com.ulfric.plugin.factions.factions.members;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.commons.value.Bean;

public class Membership extends Bean implements Comparable<Membership> {

	private UUID uniqueId;
	private Instant joined;
	private List<String> roles;

	public UUID getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Instant getJoined() {
		return joined;
	}

	public void setJoined(Instant joined) {
		this.joined = joined;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getName() {
		return PlayerHelper.getCachedName(getUniqueId());
	}

	@Override
	public int compareTo(Membership that) {
		if (this.joined == null) {
			if (that.joined == null) {
				return 0;
			}

			return -1;
		}

		if (that.joined == null) {
			return 1;
		}

		return joined.compareTo(that.joined);
	}

}
