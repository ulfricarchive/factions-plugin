package com.ulfric.plugin.factions.model.ban;

import java.util.UUID;

import com.ulfric.plugin.factions.model.CreatedBean;

public class Ban extends CreatedBean {

	private String reason;
	private UUID sender;
	private UUID target;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID sender) {
		this.sender = sender;
	}

	public UUID getTarget() {
		return target;
	}

	public void setTarget(UUID target) {
		this.target = target;
	}

}
