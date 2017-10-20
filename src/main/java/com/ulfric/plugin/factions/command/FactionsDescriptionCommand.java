package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.text.RegexHelper;
import com.ulfric.dragoon.rethink.response.ResponseHelper;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

@Name("description")
@Alias("desc")
@FactionsPermission("")
public class FactionsDescriptionCommand extends DenizenFactionFactionsCommand {

	public static final Pattern ALLOWED_DESCRIPTION_CHARACTERS = RegexHelper.compile("[a-zA-Z0-9 _\\.\\!\\?\\-\\#\\$\\,\\/\\%]+");

	private String newDescription;

	@Override
	public Future<?> runAsFaction() {
		loadDescriptionFromContext();

		if (newDescription == null) {
			Component description = faction.removeComponent(DescriptionComponent.KEY);

			if (description == null) {
				tell("factions-description-example");
				return null;
			}
		} else {
			if (!RegexHelper.matches(newDescription, ALLOWED_DESCRIPTION_CHARACTERS)) {
				tell("factions-description-illegal-characters");
				return null;
			}

			DescriptionComponent description = faction.getComponent(DescriptionComponent.KEY);
			if (description != null) {
				if (Objects.equals(description.getDescription(), newDescription)) {
					tell("factions-description-already-set");
					return null;
				}
			}
			else {
				description = new DescriptionComponent();
				faction.addComponent(description);
			}
			description.setWrittenBy(uniqueId());
			description.setDescription(newDescription);
		}

		return Factions.saveDenizen(faction).whenComplete((saved, saveError) -> {
			if (saveError != null || !ResponseHelper.changedData(saved)) {
				if (newDescription == null) {
					tell("factions-description-delete-save-error");
				} else {
					tell("factions-description-save-error");
				}

				return;
			}

			// TODO tell the whole faction?
			if (newDescription == null) {
				tell("factions-description-deleted");
			} else {
				tell("factions-description-saved");
			}
		});
	}

	private void loadDescriptionFromContext() {
		List<String> descriptionWords = context.getArguments().getArguments().get(FactionsDescriptionCommand.class);
		if (CollectionUtils.isEmpty(descriptionWords)) {
			return;
		}

		this.newDescription = descriptionWords.stream().collect(Collectors.joining(" "));
	}

}
