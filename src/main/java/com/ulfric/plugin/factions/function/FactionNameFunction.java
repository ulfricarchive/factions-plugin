package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionNameFunction extends FactionFunction {

	public FactionNameFunction() {
		super("name");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getIdentifier();
	}

}
