package com.ulfric.plugin.factions.command;

import java.util.UUID;
import java.util.concurrent.Future;

import com.ulfric.dragoon.rethink.DocumentHelper;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.command.argument.Denizen;

public abstract class DenizenFactionTargetFactionsCommand extends DenizenFactionFactionsCommand {

	@Denizen
	@Argument
	protected Entity target;

	protected UUID targetUniqueId;

	@Override
	public Future<?> runAsDenizen() {
		if (denizen.equals(target)) {
			tell("factions-cannot-be-self");
			return null;
		}

		resolveUniqueIdOfTarget();
		if (targetUniqueId == null) {
			tell("factions-could-not-find-denizen-id");
			return null;
		}

		return super.runAsDenizen();
	}

	private void resolveUniqueIdOfTarget() {
		targetUniqueId = DocumentHelper.getUniqueId(target);
	}

}
