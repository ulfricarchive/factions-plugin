package com.ulfric.plugin.factions.command;

import java.util.concurrent.Future;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.factions.description.DescriptionColor;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

@Name("color")
@Alias("colour")
@FactionsPermission("description-color")
public class FactionsDescriptionColorCommand extends FactionsDescriptionCommand {

	@Argument(message = "factions-description-color-required")
	private DescriptionColor color;

	@Override
	public Future<?> runAsFaction() {
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

		return Factions.saveDenizen(faction).whenComplete((saved, saveError) -> {
			if (saveError != null || !ResponseHelper.changedData(saved)) {
				tell("factions-description-color-save-error");
			}

			// TODO tell the whole faction?
			tell("factions-description-color-saved");
		});
	}

}
