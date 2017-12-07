package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.CommandHelp;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.entity.Denizen;

@Name("factions")
@Alias({ "faction", "facs", "fac", "f", "team", "guild", "gang", "group" })
public class FactionsCommand extends CommandHelp {

	@Inject
	protected Factions factions;

	protected Denizen denizen;

}
