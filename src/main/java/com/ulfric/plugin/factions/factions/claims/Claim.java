package com.ulfric.plugin.factions.factions.claims;

import java.util.UUID;

import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.value.Bean;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.spatialregions.shape.Point2d;
import com.ulfric.spatialregions.shape.Shape;
import com.ulfric.spatialregions.shape.Square;

public class Claim extends Bean {

	public static Region createRegion(Instance<Entity> entity, Claim claim) {
		return Region.builder()
			.setName(claim.getId().toString())
			.setFlags(new OwnerFlag(entity))
			.setBounds(claim.toShape())
			.build();
	}

	private UUID id;
	private UUID world;
	private Coordinate2d min;
	private Coordinate2d max;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public Shape toShape() {
		Point2d x = point(min);
		Point2d z = point(max);

		return new Square(x, z);
	}

	private Point2d point(Coordinate2d coordinate) {
		return coordinate == null ? Point2d.ZERO : coordinate.toPoint2d();
	}

}
