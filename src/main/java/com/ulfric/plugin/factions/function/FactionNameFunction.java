package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.entity.Faction;

public class FactionNameFunction extends Function<Faction> {

	public FactionNameFunction() {
		super("name", Faction.class);
	}

	@Override
	public Object apply(Faction faction) {
		return faction.getIdentifier();
	}

}
