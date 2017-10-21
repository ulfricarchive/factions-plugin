package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;

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

			if (!hasFactionPermissions()) {
				tell("factions-no-permission");
				return;
			}

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

	protected final boolean hasFactionPermissions() {
		for (FactionsPermission permission : Stereotypes.getAll(getClass(), FactionsPermission.class)) {
			if (!hasFactionPermission(permission.value())) {
				return false;
			}
		}

		return true;
	}

	protected final boolean hasFactionPermission(String permission) {
		permission = permission.toLowerCase();
		return MembersComponent.getPermissions(faction, uniqueId()).hasPermission(permission);
	}

	protected final void tellFaction(String message) {
		Factions.tellFaction(faction, message, details());
	}

	protected final CompletableFuture<Response> saveFaction() {
		return Factions.saveFaction(faction);
	}

}
