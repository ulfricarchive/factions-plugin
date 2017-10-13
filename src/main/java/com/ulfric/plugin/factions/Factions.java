package com.ulfric.plugin.factions;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.denizens.DenizensSystem;
import com.ulfric.plugin.factions.factions.FactionsSystem;
import com.ulfric.plugin.services.Service;

public class Factions implements Service<Factions> {

	public static Factions get() {
		return Service.get(Factions.class);
	}

	public static CompletableFuture<Entity> getDenizen(UUID uniqueId) {
		return get().getDenizenByUniqueId(uniqueId);
	}

	public static CompletableFuture<Response> saveDenizen(Entity denizen) {
		return get().persistDenizen(denizen);
	}

	@Inject
	private FactionsSystem factions;

	@Inject
	private DenizensSystem denizens;

	public CompletableFuture<Entity> getDenizenByUniqueId(UUID uniqueId) {
		return denizens.createEntity(uniqueId).thenApply(Instance::get);
	}

	public CompletableFuture<Response> persistDenizen(Entity entity) {
		return denizens.persist(entity);
	}

	@Override
	public Class<Factions> getService() {
		return Factions.class;
	}

}
