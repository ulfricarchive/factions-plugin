package com.ulfric.plugin.factions.command;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.BooleanUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.confirmation.RequireConfirmation;

@Name("open")
@FactionPermission("open")
@RequireConfirmation(duration = 20, unit = TimeUnit.SECONDS, message = "factions-open-confirmation-required")
public class FactionsOpenCommand extends FactionFactionsCommand {

	@Override
	public void run() {
		if (BooleanUtils.isTrue(faction.getOpen())) {
			tell("factions-open-already-open");
			return;
		}

		faction.setOpen(false);
		factions.persistFaction(faction);

		tell("factions-open");
		tellDenizensExceptForSender("factions-open-by");
	}

}
