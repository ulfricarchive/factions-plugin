package com.ulfric.plugin.factions.controller.role;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.controller.FactionFactionsCommand;
import com.ulfric.plugin.factions.controller.FactionPermission;

@Name("roles")
@Alias("groups")
@FactionPermission("role-list")
public class FactionsRolesCommand extends FactionFactionsCommand {

	@Override
	public void runCommandWithFaction() {
		tell("factions-role-list");
	}

}
