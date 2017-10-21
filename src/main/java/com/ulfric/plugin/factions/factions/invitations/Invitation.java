package com.ulfric.plugin.factions.factions.invitations;

import java.time.Instant;
import java.util.UUID;

import com.ulfric.commons.value.Bean;

public class Invitation extends Bean implements Comparable<Invitation> {

	private Instant created;
	private UUID inviter;
	private UUID invited;

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public UUID getInviter() {
		return inviter;
	}

	public void setInviter(UUID inviter) {
		this.inviter = inviter;
	}

	public UUID getInvited() {
		return invited;
	}

	public void setInvited(UUID invited) {
		this.invited = invited;
	}

	@Override
	public int compareTo(Invitation that) {
		if (this.created == null) {
			if (that.created == null) {
				return 0;
			}

			return -1;
		}

		if (that.created == null) {
			return 1;
		}

		return this.created.compareTo(that.created);
	}

}
