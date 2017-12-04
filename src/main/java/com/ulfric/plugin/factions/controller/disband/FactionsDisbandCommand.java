package com.ulfric.plugin.factions.controller.disband;

import java.util.concurrent.TimeUnit;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.BeanHelper;
import com.ulfric.plugin.commands.confirmation.RequireConfirmation;
import com.ulfric.plugin.factions.controller.FactionFactionsCommand;
import com.ulfric.plugin.factions.controller.FactionPermission;

@Name("disband")
@FactionPermission("disband")
@RequireConfirmation(duration = 10, unit = TimeUnit.SECONDS, message = "factions-disband-confirmation-required")
public class FactionsDisbandCommand extends FactionFactionsCommand {

	@Override
	public void runCommandWithFaction() {
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
