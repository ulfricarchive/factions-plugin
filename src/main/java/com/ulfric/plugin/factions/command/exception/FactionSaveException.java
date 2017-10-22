package com.ulfric.plugin.factions.command.exception;

import com.ulfric.dragoon.rethink.response.Response;

public class FactionSaveException extends RuntimeException {

	public FactionSaveException(String message, Response cause) {
		super(message + ' ' + cause);
	}

}
