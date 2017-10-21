package com.ulfric.plugin.factions.command;

import java.util.concurrent.Future;

import com.google.common.util.concurrent.Futures;
import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.entities.components.name.NameComponent;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionCreateException;
import com.ulfric.plugin.factions.factions.system.SystemComponent;

@Name("system")
@Permission("factions-create-system")
public class FactionsCreateSystemCommand extends FactionsCreateCommand {

	@Override
	public void run() {
		if (name == null) {
			tell("factions-create-system-name-required");
			return;
		}

		super.run();
	}

	@Override
	public Future<?> runAsDenizen() { // TODO reduce code duplication
		return Factions.getFaction(name).whenComplete((faction, createError) -> {
			if (createError != null || faction == null) {
				tell("factions-create-failed");
				return;
			}

			Details details = details();
			details.add("faction", faction);

			if (faction.hasComponent(NameComponent.KEY)) {
				tell("factions-create-already-exists", details);
				return;
			}

			faction.addComponent(formalName());
			faction.addComponent(new SystemComponent());

			Future<?> factionWait = Factions.saveFaction(faction)
					.thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionCreateException("Failed to save faction", response);
						}
					}).thenRun(() -> tell("factions-create-system", details))
					.exceptionally(exception -> {
						exception.printStackTrace(); // TODO error handling
						details.add("error", exception);
						tell("factions-error-save", details);
						return null;
					});
			Futures.getUnchecked(factionWait);
		});
	}

}
