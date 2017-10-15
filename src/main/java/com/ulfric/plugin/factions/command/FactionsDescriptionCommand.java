package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.bukkit.command.CommandSender;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.RegexHelper;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;
import com.ulfric.plugin.locale.TellService;

@Name("description")
@Alias("desc")
public class FactionsDescriptionCommand extends DenizenFactionsCommand { // TODO cleanup

	public static final Pattern ALLOWED_DESCRIPTION_CHARACTERS = RegexHelper.compile("[a-zA-Z0-9 _\\.\\!\\?\\-\\#\\$\\,\\/\\%]+");

	@Override
	public Future<?> runAsDenizen(Context context, Entity denizen) {
		CommandSender sender = context.getSender();

		String newDescription = descriptionFromContext(context);
		if (newDescription == null) {
			Component description = denizen.getComponents().remove(DescriptionComponent.KEY);

			if (description == null) {
				TellService.sendMessage(sender, "factions-description-example");
				return null;
			}
		} else {
			if (!RegexHelper.matches(newDescription, ALLOWED_DESCRIPTION_CHARACTERS)) {
				TellService.sendMessage(sender, "factions-description-illegal-characters");
				return null;
			}

			DescriptionComponent description = denizen.getComponent(DescriptionComponent.KEY);
			if (description != null) {
				if (Objects.equals(description.getDescription(), newDescription)) {
					TellService.sendMessage(sender, "factions-description-already-set");
					return null;
				}
			}
			else {
				description = new DescriptionComponent();
				denizen.addComponent(description);
			}
			description.setDescription(newDescription);
		}

		return Factions.saveDenizen(denizen).whenComplete((saved, saveError) -> {
			if (saveError != null || !ResponseHelper.changedData(saved)) {
				if (newDescription == null) {
					TellService.sendMessage(sender, "factions-description-delete-save-error");
				} else {
					TellService.sendMessage(sender, "factions-description-save-error");
				}

				return;
			}

			if (newDescription == null) {
				TellService.sendMessage(sender, "factions-description-deleted");
			} else {
				TellService.sendMessage(sender, "factions-description-saved");
			}
		});
	}

	private String descriptionFromContext(Context context) {
		List<String> descriptionWords = context.getArguments().getArguments().get(FactionsDescriptionCommand.class);
		if (CollectionUtils.isEmpty(descriptionWords)) {
			return null;
		}

		return descriptionWords.stream().collect(Collectors.joining(" "));
	}

}
