package com.ulfric.plugin.factions.factions.claims;

import com.ulfric.commons.value.Bean;
import com.ulfric.spatialregions.shape.Point2d;

public class Coordinate2d extends Bean {

	private Integer x;
	private Integer z;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}

	public Point2d toPoint2d() {
		return Point2d.builder()
				.setX(x())
				.setZ(z())
				.build();
	}

	private int x() {
		Integer x = getX();
		return x == null ? 0 : x;
	}

	public int z() {
		Integer z = getZ();
		return z == null ? 0 : z;
	}

}
