package com.ulfric.plugin.factions.command;

import java.util.UUID;

import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.model.Denizen;

public abstract class TargetDenizenFactionFactionsCommand extends FactionFactionsCommand {

	@Argument
	protected Denizen target;

	protected UUID targetUniqueId() {
		return UniqueIdHelper.parseUniqueIdExact(target.getIdentifier());
	}

	protected void tellTarget(String message) {
		tell(target, message);
	}

}
