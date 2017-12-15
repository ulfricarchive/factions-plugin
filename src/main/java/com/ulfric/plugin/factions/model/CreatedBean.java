package com.ulfric.plugin.factions.model;

import java.time.Instant;

import com.ulfric.commons.value.Bean;

public class CreatedBean extends Bean {

	private Instant creation;

	public Instant getCreation() {
		return creation;
	}

	public void setCreation(Instant creation) {
		this.creation = creation;
	}

}
