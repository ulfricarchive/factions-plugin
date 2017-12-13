package com.ulfric.plugin.factions.command;

import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;

public abstract class TargetFactionFactionsCommand extends FactionsCommand {

	@Argument(message = "factions-target-required")
	protected Faction target;

	protected void tellTarget(String message) {
		EntityHelper.getOnlineDenizens(target).forEach(player -> tell(player, message));
	}

}
