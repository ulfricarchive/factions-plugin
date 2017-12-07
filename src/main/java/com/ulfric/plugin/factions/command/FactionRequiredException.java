package com.ulfric.plugin.factions.command;

import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.exception.CommandException;

public class FactionRequiredException extends CommandException {

	public FactionRequiredException(Context context) {
		super(context);
	}

}
