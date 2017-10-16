package com.ulfric.plugin.factions;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.denizens.DenizenSystem;
import com.ulfric.plugin.factions.factions.FactionSystem;
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

	public static CompletableFuture<Entity> getFaction(String name) {
		return get().getFactionByName(name);
	}

	public static CompletableFuture<Response> saveFaction(Entity faction) {
		return get().persistFaction(faction);
	}

	@Inject
	private FactionSystem factions;

	@Inject
	private DenizenSystem denizens;

	public CompletableFuture<Entity> getDenizenByUniqueId(UUID uniqueId) {
		return denizens.createEntity(uniqueId).thenApply(Instance::get);
	}

	public CompletableFuture<Response> persistDenizen(Entity denizen) {
		return denizens.persist(denizen);
	}

	public CompletableFuture<Entity> getFactionByName(String name) {
		return factions.createEntity(name.toLowerCase()).thenApply(Instance::get);
	}

	public CompletableFuture<Response> persistFaction(Entity faction) {
		return factions.persist(faction);
	}

	@Override
	public Class<Factions> getService() {
		return Factions.class;
	}

}
