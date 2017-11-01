package com.ulfric.plugin.factions.factions.claims;

import java.util.Objects;

import com.ulfric.commons.spatial.flag.Flags;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.plugin.entities.Entity;

public final class OwnerFlag extends Flags {

	private final Instance<Entity> instance;

	public OwnerFlag(Instance<Entity> instance) {
		Objects.requireNonNull(instance, "instance");

		this.instance = instance;
	}

	public Instance<Entity> getOwner() {
		return instance;
	}

}
