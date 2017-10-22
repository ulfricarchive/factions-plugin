package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.confirmation.RequireConfirmation;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;

@Name("disband")
@FactionsPermission("disband")
@RequireConfirmation(duration = 30, unit = TimeUnit.SECONDS, message = "factions-disband-confirmation-required")
public class FactionsDisbandCommand extends DenizenFactionFactionsCommand {

	@Override
	public CompletableFuture<?> runAsFaction() {
		return Factions.disbandFaction(faction).thenAccept(saved -> {
			if (!ResponseHelper.changedData(saved)) {
				throw new FactionSaveException("Failed to disband faction", saved);
			}

			tellFaction("factions-disbanded");
		});
	}

}
