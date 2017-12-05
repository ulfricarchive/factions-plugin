package com.ulfric.plugin.factions.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.plugin.factions.entity.Denizen;
import com.ulfric.plugin.factions.entity.Faction;

public abstract class FactionFactionsCommand extends FactionsCommand {

	protected Faction faction;
	protected Set<String> permissions;

	protected Set<UUID> getDenizenUniqueIds() {
		Map<UUID, Set<String>> members = faction.getMembersToRoles();

		if (members == null) {
			return Collections.emptySet();
		}

		return members.keySet();
	}

	protected Stream<Denizen> getDenizens() {
		return getDenizenUniqueIds()
				.stream()
				.map(factions::getDenizen);
	}

	protected Stream<Player> getOnlineDenizens() {
		return getDenizenUniqueIds()
				.stream()
				.map(Bukkit::getPlayer)
				.filter(Objects::nonNull);
	}

	protected void tellDenizensExceptForSender(String message) {
		UUID player = uniqueId();
		getOnlineDenizens()
			.filter(denizen -> !denizen.getUniqueId().equals(player))
			.forEach(denizen -> tell(denizen, message));
	}

}
