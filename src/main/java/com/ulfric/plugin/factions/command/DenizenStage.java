package com.ulfric.plugin.factions.command;

import org.bukkit.event.EventHandler;

import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.stage.VoidStage;

public class DenizenStage extends VoidStage {

	@EventHandler(ignoreCancelled = true)
	public void on(CommandPreRunEvent event) {
		Command command = event.getContext().getCommand();
		if (command instanceof FactionsCommand) {
			FactionsCommand factionsCommand = (FactionsCommand) command;

			if (factionsCommand.denizen == null) {
				factionsCommand.denizen = factionsCommand.factions.getDenizen(factionsCommand.player());
			}
		}
	}

}
