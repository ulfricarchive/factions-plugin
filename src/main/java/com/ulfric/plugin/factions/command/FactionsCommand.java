package com.ulfric.plugin.factions.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.CommandHelp;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Denizen;

@Name("factions")
@Alias({ "faction", "facs", "fac", "f", "team", "guild", "gang", "group" })
public class FactionsCommand extends CommandHelp {

	@Inject
	protected Factions factions;

	protected Denizen denizen;

	protected void tell(Denizen denizen, String message) {
		Player player = Bukkit.getPlayer(UniqueIdHelper.parseUniqueIdExact(denizen.getIdentifier()));
		if (player != null) {
			tell(player, message);
		}
	}

}
