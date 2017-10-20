package com.ulfric.plugin.factions.command.exception;

import com.ulfric.dragoon.rethink.response.Response;

public class FactionCreateException extends RuntimeException {

	public FactionCreateException(String message, Response cause) {
		super(message + ' ' + cause);
	}

}