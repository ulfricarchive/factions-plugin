package com.ulfric.plugin.factions.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;

public abstract class DenizenFactionsCommand extends FactionsCommand {

	protected Entity denizen;

	@Override
	public void run() {
		Factions.getDenizen(uniqueId()).whenComplete((denizen, error) -> {
			if (error != null || denizen == null) {
				tell("factions-error-denizen-not-found");
				return;
			}

			this.denizen = denizen;
			Future<?> run = runAsDenizen();

			if (run == null) {
				return;
			}

			try {
				run.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e); // TODO error handling
			}
		});
	}

	public abstract Future<?> runAsDenizen();

}
