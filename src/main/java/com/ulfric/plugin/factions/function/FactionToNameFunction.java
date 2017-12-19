package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionToNameFunction extends FactionFunction {

	public FactionToNameFunction() {
		super("name");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getIdentifier();
	}

}
