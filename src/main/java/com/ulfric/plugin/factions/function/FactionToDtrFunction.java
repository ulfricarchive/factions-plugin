package com.ulfric.plugin.factions.function;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.dtr.RaidHelper;

public class FactionToDtrFunction extends FactionFunction {

	@Inject
	private RaidHelper raidHelper;

	public FactionToDtrFunction() {
		super("dtr");
	}

	@Override
	public Object apply(Faction faction) {
		return raidHelper.calculateDtr(faction);
	}

}
