package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.Faction;

public class FactionDtrMetaFunction extends FactionFunction {

	public FactionDtrMetaFunction() {
		super("dtrMeta");
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getDeathsTillRaidable();
	}

}
