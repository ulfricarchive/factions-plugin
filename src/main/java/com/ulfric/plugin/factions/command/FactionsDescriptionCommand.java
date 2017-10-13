package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.RegexHelper;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;
import com.ulfric.plugin.locale.TellService;

@Name("description")
@Alias("desc")
public class FactionsDescriptionCommand extends FactionsCommand {

	public static final Pattern ALLOWED_DESCRIPTION_CHARACTERS = RegexHelper.compile("[a-zA-Z0-9 _\\.\\!\\?\\-\\#\\$\\,\\/\\%]+");

	@Override
	public void run(Context context) {
		Player player = Context.getPlayer(context);

		Factions.getDenizen(player.getUniqueId()).whenComplete((denizen, error) -> {
			if (error != null) {
				TellService.sendMessage(player, "factions-error-denizen-not-found");
				return;
			}

			String newDescription = descriptionFromContext(context);
			if (newDescription == null) {
				Component description = denizen.getComponents().remove(DescriptionComponent.KEY);

				if (description == null) {
					TellService.sendMessage(player, "factions-description-example");
					return;
				}
			} else {
				if (!RegexHelper.matches(newDescription, ALLOWED_DESCRIPTION_CHARACTERS)) {
					TellService.sendMessage(player, "factions-description-illegal-characters");
					return;
				}

				DescriptionComponent description = denizen.getComponent(DescriptionComponent.KEY);
				if (description != null) {
					if (Objects.equals(description.getDescription(), newDescription)) {
						TellService.sendMessage(player, "factions-description-already-set");
						return;
					}
				}
				else {
					description = new DescriptionComponent();
					denizen.addComponent(description);
				}
				description.setDescription(newDescription);
			}

			Factions.saveDenizen(denizen).whenComplete((saved, saveError) -> {
				if (saveError != null || !ResponseHelper.changedData(saved)) {
					if (newDescription == null) {
						TellService.sendMessage(player, "factions-description-delete-save-error");
					} else {
						TellService.sendMessage(player, "factions-description-save-error");
					}

					return;
				}

				if (newDescription == null) {
					TellService.sendMessage(player, "factions-description-deleted");
				} else {
					TellService.sendMessage(player, "factions-description-saved");
				}
			});
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
