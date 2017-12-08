package com.ulfric.plugin.factions.command;

import org.apache.commons.lang3.BooleanUtils;

import com.ulfric.commons.naming.Name;

@Name("close")
@FactionPermission("close")
public class FactionsCloseCommand extends FactionFactionsCommand {

	@Override
	public void run() {
		if (!BooleanUtils.isTrue(faction.getOpen())) {
			tell("factions-close-not-open");
			return;
		}

		faction.setOpen(false);
		factions.persistFaction(faction);

		tell("factions-close");
		tellDenizensExceptForSender("factions-close-by");
	}

}
