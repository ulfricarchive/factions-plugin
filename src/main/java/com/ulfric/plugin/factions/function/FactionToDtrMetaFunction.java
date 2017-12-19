package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionToDtrMetaFunction extends FactionFunction {

	public FactionToDtrMetaFunction() {
		super("dtrMeta");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getDeathsTillRaidable();
	}

}
