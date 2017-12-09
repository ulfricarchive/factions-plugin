package com.ulfric.plugin.factions.event;

import org.bukkit.event.HandlerList;

import com.ulfric.plugin.factions.model.Faction;

public class FactionDisbandEvent extends FactionEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	public FactionDisbandEvent(Faction faction) {
		super(faction);
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

}
