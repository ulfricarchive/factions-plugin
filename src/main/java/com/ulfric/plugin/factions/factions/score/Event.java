package com.ulfric.plugin.factions.factions.score;

import java.util.Map;
import java.util.UUID;

import com.ulfric.commons.value.Bean;

public class Event extends Bean { // TODO scores would really work better in something like a kafka db

	private Integer score;
	private UUID target;
	private Map<String, Object> details;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public UUID getTarget() {
		return target;
	}

	public void setTarget(UUID target) {
		this.target = target;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

}
