package com.ulfric.plugin.factions.command;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.bukkit.entity.Player;

import com.ulfric.plugin.factions.model.Denizen;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;

public abstract class FactionFactionsCommand extends FactionsCommand {

	protected Faction faction;
	protected Set<String> permissions;

	protected Stream<Player> getOnlineDenizens() {
		return EntityHelper.getOnlineDenizens(faction);
	}

	protected Stream<Denizen> getDenizens() {
		return EntityHelper.getDenizens(faction);
	}

	protected void tellDenizensExceptForSender(String message) {
		UUID player = uniqueId();
		getOnlineDenizens()
			.filter(denizen -> !denizen.getUniqueId().equals(player))
			.forEach(denizen -> tell(denizen, message));
	}

}
