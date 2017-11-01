package com.ulfric.plugin.factions.factions;

import java.util.logging.Logger;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.extension.postconstruct.PostConstruct;
import com.ulfric.dragoon.rethink.Database;
import com.ulfric.dragoon.rethink.Store;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.EntitySystem;

public class FactionSystem extends EntitySystem {

	@Inject
	@Database(table = "factions/factions")
	private Store<Entity> store;

	@Inject(optional = true)
	private Logger logger;

	@PostConstruct
	public void initialize() {
		store().listAllFromDatabase().thenAccept(list -> {
			int size = list.size();
			if (size == 0) {
				log("No factions were loaded from the database");
			} else {
				log("Loaded " + size + " factions from the database");
			}
		});
	}

	@Override
	protected Store<Entity> store() {
		return store;
	}

	private void log(String message) {
		if (logger != null) {
			logger.info(message);
		}
	}

}
