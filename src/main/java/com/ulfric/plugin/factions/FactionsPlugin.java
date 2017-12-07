package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.command.ControllerStagesContainer;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;
import com.ulfric.plugin.factions.command.FactionsDisbandCommand;
import com.ulfric.plugin.factions.command.FactionsRoleCommand;
import com.ulfric.plugin.factions.command.FactionsRolesCommand;
import com.ulfric.plugin.factions.function.FactionDescriptionFunction;
import com.ulfric.plugin.factions.function.FactionNameFunction;
import com.ulfric.plugin.factions.function.FactionRolesFunction;
import com.ulfric.plugin.factions.function.RoleNameFunction;
import com.ulfric.plugin.factions.function.RolePermissionsFunction;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);

		install(ControllerStagesContainer.class);

		install(FactionNameFunction.class);
		install(FactionDescriptionFunction.class);
		install(FactionRolesFunction.class);
		install(RoleNameFunction.class);
		install(RolePermissionsFunction.class);

		install(FactionsCommand.class);
		install(FactionsCreateCommand.class);
		install(FactionsDisbandCommand.class);
		install(FactionsDescriptionCommand.class);
		install(FactionsRoleCommand.class);
		install(FactionsRolesCommand.class);
	}

}
