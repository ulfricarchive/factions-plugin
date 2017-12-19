package com.ulfric.plugin.factions.function;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.dtr.RaidHelper;

public class FactionToRaidableFunction extends FactionFunction {

	@Inject
	private RaidHelper raidHelper;

	public FactionToRaidableFunction() {
		super("raidable");
	}

	@Override
	public Object apply(Faction faction) {
		return raidHelper.isRaidable(faction);
	}

}
