package com.ulfric.plugin.factions.controller;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;

import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.stage.VoidStage;

public class FactionStage extends VoidStage {

	@EventHandler(ignoreCancelled = true)
	public void on(CommandPreRunEvent event) {
		Command command = event.getContext().getCommand();
		if (command instanceof FactionFactionsCommand) {
			FactionFactionsCommand factionsCommand = (FactionFactionsCommand) command;

			if (factionsCommand.faction == null) {
				String factionName = factionsCommand.denizen.getFaction();
				if (StringUtils.isEmpty(factionName)) {
					event.cancel(new FactionRequiredException(event.getContext()));
					return;
				}

				factionsCommand.faction = factionsCommand.factions.getFaction(factionName);
			}
		}
	}

}
