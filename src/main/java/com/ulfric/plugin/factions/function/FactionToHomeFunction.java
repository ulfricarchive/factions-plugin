package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.territory.Territory;

public class FactionToHomeFunction extends FactionFunction {

	public FactionToHomeFunction() {
		super("home");
	}

	@Override
	public Object apply(Faction faction) {
		Territory territory = faction.getTerritory();

		if (territory == null) {
			return null;
		}

		return territory.getHome();
	}

}
