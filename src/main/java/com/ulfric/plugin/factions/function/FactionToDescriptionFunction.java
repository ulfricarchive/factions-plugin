package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionToDescriptionFunction extends FactionFunction {

	public FactionToDescriptionFunction() {
		super("description");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getDescription();
	}

}
