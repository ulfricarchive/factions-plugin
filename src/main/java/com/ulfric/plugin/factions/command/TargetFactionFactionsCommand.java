package com.ulfric.plugin.factions.command;

import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.entity.EntityHelper;
import com.ulfric.plugin.factions.entity.Faction;

public abstract class TargetFactionFactionsCommand extends FactionsCommand {

	@Argument
	protected Faction target;

	protected void tellTarget(String message) {
		EntityHelper.getOnlineDenizens(target).forEach(player -> tell(player, message));
	}

}
