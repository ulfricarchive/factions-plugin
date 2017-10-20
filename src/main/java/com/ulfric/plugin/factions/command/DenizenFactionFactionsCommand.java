package com.ulfric.plugin.factions.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;

public abstract class DenizenFactionFactionsCommand extends DenizenFactionsCommand {

	protected Entity denizen;
	protected Entity faction;

	@Override
	public Future<?> runAsDenizen() {
		MembershipComponent membership = denizen.getComponent(MembershipComponent.KEY);

		if (membership == null) {
			tell("factions-must-be-in-faction");
			return null;
		}

		return membership.getFactionEntity().whenComplete((faction, error) -> {
			if (error != null || faction == null) {
				tell("factions-error-faction-not-found");
				return;
			}

			this.faction = faction;
			Future<?> run = runAsFaction();

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

	public abstract Future<?> runAsFaction();

}
