package com.ulfric.plugin.factions;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.DocumentHelper;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.denizens.DenizenSystem;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.FactionSystem;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.locale.TellService;
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

	public static CompletableFuture<Response> disbandFaction(Entity faction) {
		Factions factions = get();
		return factions.deleteFaction(faction)
		.thenAccept(delete -> {
			if (!ResponseHelper.changedData(delete)) {
				throw new FactionSaveException("Failed to delete faction", delete);
			}
		}).thenCompose(ignore -> {
			CompletableFuture<Response> future = FutureHelper.empty();
			// TODO may want to do this in parallel
			MembersComponent members = faction.getComponent(MembersComponent.KEY);
			if (members != null) {
				for (Entity denizen : members.getMemberEntities(factions)) {
					if (denizen.removeComponent(MembershipComponent.KEY) != null) {
						future = future.thenCompose(ignore2 -> factions.persistDenizen(denizen));
					}
				}
			}

			return future;
		});
	}

	public static void tellFaction(Entity faction, String message) {
		tellFaction(faction, message, Details.none());
	}

	public static void tellFaction(Entity faction, String message, Details details) {
		MembersComponent members = faction.getComponent(MembersComponent.KEY);

		if (members == null) {
			return;
		}

		TellService tell = TellService.get();
		if (tell == null) {
			return;
		}

		members.getMembersSet()
			.stream()
			.map(Bukkit::getPlayer)
			.filter(Objects::nonNull)
			.forEach(member -> tell.send(member, message, details));
	}

	public static void tellDenizen(Entity denizen, String message) {
		tellDenizen(denizen, message, Details.none());
	}

	public static void tellDenizen(Entity denizen, String message, Details details) {
		UUID uniqueId = DocumentHelper.getUniqueId(denizen);

		if (uniqueId == null) {
			return;
		}

		Player player = Bukkit.getPlayer(uniqueId);
		if (player == null) {
			return;
		}

		TellService.sendMessage(player, message, details);
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

	public CompletableFuture<Response> deleteFaction(Entity faction) {
		return factions.delete(faction);
	}

	@Override
	public Class<Factions> getService() {
		return Factions.class;
	}

}
