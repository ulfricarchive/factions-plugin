package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;

public abstract class DenizenFactionsCommand extends FactionsCommand {

	protected Entity denizen;

	@Override
	public void run() {
		Factions.getDenizen(uniqueId()).thenCompose(denizen -> {
			if (denizen == null) {
				tell("factions-error-denizen-not-found");
				return FutureHelper.empty();
			}

			this.denizen = denizen;
			CompletableFuture<?> run = runAsDenizen();

			if (run == null) {
				return FutureHelper.empty();
			}

			return run;
		}).exceptionally(thrown -> {
			internalError("factions-internal-error", thrown);
			return null;
		});
	}

	public abstract CompletableFuture<?> runAsDenizen();

	protected final CompletableFuture<Response> saveDenizen() {
		return Factions.saveDenizen(denizen);
	}

}
