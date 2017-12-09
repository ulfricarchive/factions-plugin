package com.ulfric.plugin.factions.event;

import org.bukkit.event.Event;

import com.ulfric.plugin.factions.model.Faction;

public abstract class FactionEvent extends Event {

	private final Faction faction;

	public FactionEvent(Faction faction) {
		this.faction = faction;
	}

	public Faction getFaction() {
		return faction;
	}

}
