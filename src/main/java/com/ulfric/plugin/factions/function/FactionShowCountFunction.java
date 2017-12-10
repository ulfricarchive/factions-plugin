package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionShowCountFunction extends FactionFunction {

	public FactionShowCountFunction() {
		super("showCount");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getShowCount();
	}

}
