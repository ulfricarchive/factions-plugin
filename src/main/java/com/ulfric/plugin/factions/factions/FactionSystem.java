package com.ulfric.plugin.factions.factions;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.Database;
import com.ulfric.dragoon.rethink.Store;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.EntitySystem;

public class FactionSystem extends EntitySystem {

	@Inject
	@Database(table = "factions/factions")
	private Store<Entity> store;

	@Override
	protected Store<Entity> store() {
		return store;
	}

}
