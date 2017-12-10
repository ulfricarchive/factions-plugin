package com.ulfric.plugin.factions.command;

import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;

public class FactionsDtrCommand extends FactionsCommand {

	@Argument(optional = true)
	private Faction target;

	@Override
	public void run() {
		if (target == null) {
			target = EntityHelper.getFactionOf(denizen);

			if (target == null) {
				tell("factions-dtr-target-required");
				return;
			}
		}

		tell("factions-dtr");
	}

}
