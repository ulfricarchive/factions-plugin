package com.ulfric.plugin.factions.model.invitation;

import java.util.UUID;

import com.ulfric.plugin.factions.model.CreatedBean;

public class Invitation extends CreatedBean {

	private UUID invited;
	private UUID inviter;

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

}
