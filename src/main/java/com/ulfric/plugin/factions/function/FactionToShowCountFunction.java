package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionToShowCountFunction extends FactionFunction {

	public FactionToShowCountFunction() {
		super("showCount");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getShowCount();
	}

}
