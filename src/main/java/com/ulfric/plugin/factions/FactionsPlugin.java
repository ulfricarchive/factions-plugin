package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);
		install(FactionsCommand.class);
		install(FactionsDescriptionCommand.class);
	}

}
