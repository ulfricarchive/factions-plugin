package com.ulfric.plugin.factions.command.argument;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.factions.FactionSystem;

public class FactionResolver extends DenizenResolver {

	@Inject
	private FactionSystem factions;

	@Override
	public Entity apply(ResolutionRequest request) {
		if (!isApplicable(request)) {
			return null;
		}

		String argument = request.getArgument();
		if (argument.startsWith("#")) {
			return lookupFaction(argument.substring(1));
		}

		Entity faction = lookupFaction(argument);
		if (faction != null) {
			return faction;
		}

		return super.apply(request);
	}

	private Entity lookupFaction(String argument) {
		Instance<Entity> cached = factions.getCachedEntity(argument);
		return cached == null ? null : cached.get();
	}

	@Override
	protected boolean isApplicable(ResolutionRequest request) {
		return Stereotypes.isPresent(request.getDefinition().getField(), Faction.class);
	}

}
