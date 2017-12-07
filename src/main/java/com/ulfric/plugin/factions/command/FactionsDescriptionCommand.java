package com.ulfric.plugin.factions.command;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;

@Name("description")
@Alias("desc")
@FactionPermission("description-set")
public class FactionsDescriptionCommand extends FactionFactionsCommand {

	private String description;

	@Override
	public void run() {
		setupDescription();

		if (description == null) {
			tell("factions-description-required");
			return;
		}

		if (description.equals(faction.getDescription())) {
			tell("factions-description-already-set");
			return;
		}

		if (!StringUtils.isAsciiPrintable(description)) {
			tell("factions-description-must-be-ascii");
			return;
		}

		tellDenizensExceptForSender("factions-description-set-by");
		tell("factions-description-set");

		faction.setDescription(description);

		factions.persistFaction(faction);
	}

	private void setupDescription() {
		List<String> arguments = context.getArguments()
				.getArguments()
				.get(FactionsDescriptionCommand.class)
				.getArguments();

		if (arguments == null) {
			return;
		}

		description = arguments.stream()
				.collect(Collectors.joining(" "));
	}

}
