package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;

@Name("roles")
@Alias("groups")
@FactionPermission("role-list")
public class FactionsRolesCommand extends FactionFactionsCommand {

	@Override
	public void run() {
		tell("factions-role-list");
	}

}
