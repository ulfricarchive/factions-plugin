package com.ulfric.plugin.factions.command;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.confirmation.RequireConfirmation;
import com.ulfric.plugin.factions.Factions;

@Name("disband")
@FactionsPermission("disband")
@RequireConfirmation(duration = 30, unit = TimeUnit.SECONDS, message = "factions-disband-confirmation-required")
public class FactionsDisbandCommand extends DenizenFactionFactionsCommand {

	@Override
	public Future<?> runAsFaction() {
		return Factions.disbandFaction(faction).whenComplete((saved, saveError) -> {
			if (saveError != null || !ResponseHelper.changedData(saved)) {
				tell("factions-disband-save-error");
				return;
			}

			Factions.tellFaction(faction, "factions-disbanded", details());
		});
	}

}
