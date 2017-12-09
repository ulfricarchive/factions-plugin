package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.Faction;

public abstract class FactionFunction extends Function<Faction> {

	public FactionFunction(String name) {
		super(name, Faction.class);
	}

}
