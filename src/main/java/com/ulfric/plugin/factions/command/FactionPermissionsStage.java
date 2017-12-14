package com.ulfric.plugin.factions.command;

import java.util.List;

import org.bukkit.event.EventHandler;

import com.ulfric.commons.collection.Collectors2;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.CommandPreRunEvent;
import com.ulfric.plugin.commands.stage.Stage;
import com.ulfric.plugin.factions.model.role.PermissibleHelper;

public class FactionPermissionsStage extends Stage<List<String>> {

	@EventHandler(ignoreCancelled = true)
	public void on(CommandPreRunEvent event) {
		Command command = event.getContext().getCommand();
		if (command instanceof FactionFactionsCommand) {
			FactionFactionsCommand factionsCommand = (FactionFactionsCommand) command;

			setupPermissions(factionsCommand);

			for (String permission : get(event.getCommandType())) {
				if (!factionsCommand.permissions.contains(permission)) {
					event.cancel(new FactionMissingPermissionException(event.getContext(), permission));
					return;
				}
			}
		}
	}

	private void setupPermissions(FactionFactionsCommand command) {
		if (command.permissions == null) {
			command.permissions = PermissibleHelper.getAllPermissions(command.uniqueId(), command.factions);
		}
	}

	@Override
	protected List<String> compute(Class<? extends Command> command) {
		return Stereotypes.getAll(command, FactionPermission.class)
			.stream()
			.map(FactionPermission::value)
			.collect(Collectors2.toUnmodifiableList());
	}

}
