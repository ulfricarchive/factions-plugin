package com.ulfric.plugin.factions.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.PluginManager;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.BeanHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.confirmation.RequireConfirmation;
import com.ulfric.plugin.factions.event.FactionDisbandEvent;

@Name("disband")
@FactionPermission("disband")
@RequireConfirmation(duration = 10, unit = TimeUnit.SECONDS, message = "factions-disband-confirmation-required")
public class FactionsDisbandCommand extends FactionFactionsCommand {

	@Inject
	private PluginManager plugins;

	@Override
	public void run() {
		FactionDisbandEvent event = new FactionDisbandEvent(faction);
		plugins.callEvent(event);

		tellDenizensExceptForSender("factions-disbanded-by");
		tell("factions-disbanded");

		getDenizens()
			.forEach(denizen -> {
				denizen.setFaction(null);
				factions.persistDenizen(denizen);
			});

		String identifier = faction.getIdentifier();
		BeanHelper.nullify(faction);
		faction.setIdentifier(identifier);

		factions.persistFaction(faction);
	}

}
