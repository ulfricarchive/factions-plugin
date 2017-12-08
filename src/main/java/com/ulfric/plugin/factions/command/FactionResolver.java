package com.ulfric.plugin.factions.command;

import java.util.UUID;

import org.apache.commons.collections4.MapUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.bukkit.command.CommandSenderHelper;
import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.commands.argument.Resolver;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.entity.EntityHelper;
import com.ulfric.plugin.factions.entity.Faction;

public class FactionResolver extends Resolver<Faction> {

	@Inject
	private Factions factions;

	public FactionResolver() {
		super(Faction.class);
	}

	@Override
	public Faction apply(ResolutionRequest request) {
		String argument = request.getArgument();

		Faction faction = factions.getFaction(argument);

		if (MapUtils.isEmpty(faction.getMembersToRoles())) {
			if (faction.getPermanent()) {
				return faction;
			}
		} else {
			return faction;
		}

		UUID uniqueId;
		if (PlayerHelper.isAskingForSelf(argument)) {
			uniqueId = CommandSenderHelper.getUniqueId(request.getContext().getSender());
		} else {
			uniqueId = UniqueIdHelper.parseUniqueId(argument);
		}

		if (uniqueId == null) {
			uniqueId = PlayerHelper.getCachedUniqueId(argument);

			if (uniqueId == null) {
				Player onlinePlayer = Bukkit.getPlayer(argument);

				if (onlinePlayer == null) {
					return null;
				}

				uniqueId = onlinePlayer.getUniqueId();
			}
		}

		return EntityHelper.getFactionOf(factions.getDenizen(uniqueId));
	}

}
