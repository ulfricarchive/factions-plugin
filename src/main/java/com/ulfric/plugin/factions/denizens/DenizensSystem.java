package com.ulfric.plugin.factions.denizens;

import com.ulfric.dragoon.rethink.Database;
import com.ulfric.dragoon.rethink.Store;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.EntitySystem;

public class DenizensSystem extends EntitySystem {

	@Database(table = "factions")
	private Store<Entity> store;

	@Override
	protected Store<Entity> store() {
		return store;
	}

}
