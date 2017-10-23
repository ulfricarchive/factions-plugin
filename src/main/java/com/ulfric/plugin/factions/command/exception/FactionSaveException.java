package com.ulfric.plugin.factions.command.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ulfric.dragoon.rethink.response.Response;

public class FactionSaveException extends RuntimeException {

	public FactionSaveException(String message, Response cause) {
		super(message + ' ' + ToStringBuilder.reflectionToString(cause));
	}

}
