package com.ulfric.plugin.factions.model.territory;

import com.ulfric.commons.value.Bean;

public class Location extends Bean {

	private String world; // TODO convert to UUID?
	private Integer x;
	private Integer y;
	private Integer z;

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

}
