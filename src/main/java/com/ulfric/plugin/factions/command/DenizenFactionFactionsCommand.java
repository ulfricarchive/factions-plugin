package com.ulfric.plugin.factions.command;

import java.util.concurrent.CompletableFuture;

import com.ulfric.commons.concurrent.FutureHelper;
import com.ulfric.dragoon.rethink.response.Response;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;

public abstract class DenizenFactionFactionsCommand extends DenizenFactionsCommand {

	protected Entity faction;

	@Override
	public CompletableFuture<?> runAsDenizen() {
		MembershipComponent membership = denizen.getComponent(MembershipComponent.KEY);

		if (membership == null) {
			tell("factions-must-be-in-faction");
			return null;
		}

		return membership.getFactionEntity().thenCompose(faction -> {
			if (faction == null) {
				tell("factions-faction-not-found");
				return FutureHelper.empty();
			}

			this.faction = faction;

			if (!hasFactionPermissions()) {
				tell("factions-no-permission");
				return FutureHelper.empty();
			}

			CompletableFuture<?> run = runAsFaction();

			if (run == null) {
				return FutureHelper.empty();
			}

			return run;
		});
	}

	public abstract CompletableFuture<?> runAsFaction();

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
		return MembersComponent.getPermissions(denizen, uniqueId()).hasPermission(permission);
	}

	protected final void tellFaction(String message) {
		Factions.tellFaction(faction, message, details());
	}

	protected final CompletableFuture<Response> saveFaction() {
		return Factions.saveFaction(faction);
	}

}
