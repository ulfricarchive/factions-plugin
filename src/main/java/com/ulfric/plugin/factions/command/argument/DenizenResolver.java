package com.ulfric.plugin.factions.command.argument;

import java.util.UUID;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.rethink.Instance;
import com.ulfric.dragoon.rethink.Location;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.commands.argument.Resolver;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.denizens.DenizenSystem;

public class DenizenResolver extends Resolver<Entity> {

	@Inject
	private DenizenSystem denizens;

	public DenizenResolver() {
		super(Entity.class);
	}

	@Override
	public Entity apply(ResolutionRequest request) {
		if (!isApplicable(request)) {
			return null;
		}

		String key = request.getArgument();

		UUID uniqueId = PlayerHelper.getCachedUniqueId(key);
		if (uniqueId != null) {
			Instance<Entity> cached = denizens.getCachedEntity(Location.key(uniqueId));
			return cached == null ? null : cached.get();
		}

		return null;
	}

	protected boolean isApplicable(ResolutionRequest request) {
		return Stereotypes.isPresent(request.getDefinition().getField(), Denizen.class);
	}

}
