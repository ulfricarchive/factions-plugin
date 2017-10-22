package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.entities.components.name.NameComponent;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
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
	public CompletableFuture<?> runAsDenizen() { // TODO reduce code duplication
		return Factions.getFaction(name).thenCompose(faction -> {
			if (faction == null) {
				tell("factions-create-failed");
				return FutureHelper.empty();
			}

			Details details = details();
			details.add("faction", faction);

			if (faction.hasComponent(NameComponent.KEY)) {
				tell("factions-create-already-exists", details);
				return FutureHelper.empty();
			}

			faction.addComponent(formalName());
			faction.addComponent(new SystemComponent());

			return Factions.saveFaction(faction)
					.thenAccept(response -> {
						if (!ResponseHelper.changedData(response)) {
							throw new FactionSaveException("Failed to save faction", response);
						}
					})
					.thenRun(() -> tell("factions-create-system", details));
		});
	}

}
