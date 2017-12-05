package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.controller.ControllerStagesContainer;
import com.ulfric.plugin.factions.controller.FactionsCommand;
import com.ulfric.plugin.factions.controller.FactionsCreateCommand;
import com.ulfric.plugin.factions.controller.FactionsDisbandCommand;
import com.ulfric.plugin.factions.function.FactionNameFunction;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);

		install(ControllerStagesContainer.class);

		install(FactionNameFunction.class);

		install(FactionsCommand.class);
		install(FactionsCreateCommand.class);
		install(FactionsDisbandCommand.class);
	}

}
