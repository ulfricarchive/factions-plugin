package com.ulfric.plugin.factions;

import java.util.UUID;
import java.util.stream.Stream;

import org.bukkit.OfflinePlayer;

import com.ulfric.dragoon.acrodb.Database;
import com.ulfric.dragoon.acrodb.Store;
import com.ulfric.dragoon.conf4j.Settings;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.model.Denizen;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.settings.FactionsSettings;
import com.ulfric.plugin.services.Service;

public class Factions implements Service<Factions> {

	public static Factions get() {
		return Service.get(Factions.class);
	}

	@Inject
	@Database({ "factions", "denizens" })
	private Store<Denizen> denizens;

	@Inject
	@Database({ "factions", "factions" })
	private Store<Faction> factions;

	@Settings
	private FactionsSettings settings;

	@Override
	public Class<Factions> getService() {
		return Factions.class;
	}

	public Store<Denizen> getDenizens() {
		return denizens;
	}

	public Store<Faction> getFactions() {
		return factions;
	}

	public Denizen getDenizen(OfflinePlayer player) {
		return getDenizen(player.getUniqueId());
	}

	public Denizen getDenizen(UUID uniqueId) {
		return getDenizens().get(uniqueId.toString());
	}

	public void persistDenizen(Denizen denizen) {
		getDenizens().persist(denizen);
	}

	public Faction getFaction(String name) {
		return getFactions().get(name);
	}

	public Stream<Faction> getAllFactions() {
		return getFactions().getAllDocuments();
	}

	public void persistFaction(Faction faction) {
		getFactions().persist(faction);
	}

	public FactionsSettings settings() {
		return settings;
	}

}
