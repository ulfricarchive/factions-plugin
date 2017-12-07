package com.ulfric.plugin.factions.command;

import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.exception.CommandException;

public class FactionMissingPermissionException extends CommandException {

	private final String missingPermission;

	public FactionMissingPermissionException(Context context, String missingPermission) {
		super(context);

		this.missingPermission = missingPermission;
	}

	public String getMissingPermission() {
		return missingPermission;
	}

}
