package com.ulfric.plugin.factions.factions.claims;

import java.util.UUID;

import com.ulfric.commons.value.Bean;

public class Claim extends Bean {

	private UUID world;
	private Coordinate2d min;
	private Coordinate2d max;

	public UUID getWorld() {
		return world;
	}

	public void setWorld(UUID world) {
		this.world = world;
	}

	public Coordinate2d getMin() {
		return min;
	}

	public void setMin(Coordinate2d min) {
		this.min = min;
	}

	public Coordinate2d getMax() {
		return max;
	}

	public void setMax(Coordinate2d max) {
		this.max = max;
	}

}
