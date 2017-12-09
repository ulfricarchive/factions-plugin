package com.ulfric.plugin.factions.command;

import java.util.UUID;

import com.ulfric.commons.bukkit.command.CommandSenderHelper;
import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.commands.argument.Resolver;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Denizen;

public class DenizenResolver extends Resolver<Denizen> {

	@Inject
	private Factions factions;

	public DenizenResolver() {
		super(Denizen.class);
	}

	@Override
	public Denizen apply(ResolutionRequest request) {
		String argument = request.getArgument();

		if (PlayerHelper.isAskingForSelf(argument)) {
			return factions.getDenizen(CommandSenderHelper.getUniqueId(request.getContext().getSender()));
		}

		UUID uniqueId = UniqueIdHelper.parseUniqueId(argument);
		if (uniqueId == null) {
			uniqueId = PlayerHelper.getCachedUniqueId(argument);

			if (uniqueId == null) {
				return null;
			}
		}

		return factions.getDenizen(uniqueId);
	}

}
