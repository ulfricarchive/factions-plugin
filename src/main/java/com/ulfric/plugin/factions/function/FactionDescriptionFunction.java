package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.entity.Faction;

public class FactionDescriptionFunction extends FactionFunction {

	public FactionDescriptionFunction() {
		super("description");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getDescription();
	}

}
