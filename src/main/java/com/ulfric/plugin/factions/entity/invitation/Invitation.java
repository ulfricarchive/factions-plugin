package com.ulfric.plugin.factions.entity.invitation;

import java.time.Instant;
import java.util.UUID;

import com.ulfric.commons.value.Bean;

public class Invitation extends Bean {

	private UUID invited;
	private UUID inviter;
	private Instant creation;

	public UUID getInvited() {
		return invited;
	}

	public void setInvited(UUID invited) {
		this.invited = invited;
	}

	public UUID getInviter() {
		return inviter;
	}

	public void setInviter(UUID inviter) {
		this.inviter = inviter;
	}

	public Instant getCreation() {
		return creation;
	}

	public void setCreation(Instant creation) {
		this.creation = creation;
	}

}
