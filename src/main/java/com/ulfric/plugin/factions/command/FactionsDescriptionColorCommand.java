package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.command.exception.FactionSaveException;
import com.ulfric.plugin.factions.factions.description.DescriptionColor;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

@Name("color")
@Alias("colour")
@Permission(value = "factions-description-color", message = "factions-description-color-perk")
@FactionsPermission("description-color")
public class FactionsDescriptionColorCommand extends FactionsDescriptionCommand {

	@Argument(message = "factions-description-color-required")
	private DescriptionColor color;

	@Override
	public CompletableFuture<?> runAsFaction() {
		DescriptionComponent description = faction.getComponent(DescriptionComponent.KEY);
		if (description == null) {
			description = new DescriptionComponent();
			faction.addComponent(description);
		} else {
			if (description.getColor() == color) {
				tell("factions-description-color-already");
				return null;
			}
		}
		description.setColor(color);

		return saveFaction()
				.thenAccept(saved -> {
					if (!ResponseHelper.changedData(saved)) {
						throw new FactionSaveException("Failed to save description color", saved);
					}
				})
				.thenRun(() -> tellFaction("factions-description-color-saved"));
	}

}
