package com.ulfric.plugin.factions.command;

import java.util.concurrent.Future;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.intercept.asynchronous.Asynchronous;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.command.argument.Faction;

@Name("show")
@Alias({"information", "info", "who", "details", "s"})
@Asynchronous
// TODO cooldown - Limit of 50 per 10 minutes
public class FactionsShowCommand extends DenizenFactionFactionsCommand {

	@Faction
	@Argument(optional = true)
	private Entity target;

	@Override
	public Future<?> runAsDenizen() {
		if (target == null) {
			if (triedToResolve()) {
				tell("factions-show-not-found");
				return null;
			}

			return super.runAsDenizen();
		}

		faction = target;
		return runAsFaction();
	}

	@Override
	public Future<?> runAsFaction() {
		tell("factions-show");

		return null;
	}

	private boolean triedToResolve() {
		return CollectionUtils.isNotEmpty(context.getArguments().getArguments().get(FactionsShowCommand.class));
	}

}
