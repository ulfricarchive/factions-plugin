package com.ulfric.plugin.factions.command;

import org.bukkit.event.EventHandler;

import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.stage.VoidStage;
import com.ulfric.plugin.factions.entity.EntityHelper;

public class FactionStage extends VoidStage {

	@EventHandler(ignoreCancelled = true)
	public void on(CommandPreRunEvent event) {
		Command command = event.getContext().getCommand();
		if (command instanceof FactionFactionsCommand) {
			FactionFactionsCommand factionsCommand = (FactionFactionsCommand) command;

			if (factionsCommand.faction == null) {

				factionsCommand.faction = EntityHelper.getFactionOf(factionsCommand.denizen);
				if (factionsCommand.faction == null) {
					event.cancel(new FactionRequiredException(event.getContext()));
					return;
				}
			}
		}
	}

}
