package com.ulfric.plugin.factions.entity;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.plugin.factions.Factions;

public class EntityHelper {

	public static Faction getFactionOf(Denizen denizen) {
		if (denizen == null) {
			return null;
		}

		String faction = denizen.getFaction();
		if (StringUtils.isEmpty(faction)) {
			return null;
		}

		return Factions.get().getFaction(faction);
	}

	public static Set<UUID> getDenizenUniqueIds(Faction faction) {
		Map<UUID, Set<String>> members = faction.getMembersToRoles();

		if (members == null) {
			return Collections.emptySet();
		}

		return members.keySet();
	}

	public static Stream<Denizen> getDenizens(Faction faction) {
		return getDenizenUniqueIds(faction)
				.stream()
				.map(Factions.get()::getDenizen);
	}

	public static Stream<Player> getOnlineDenizens(Faction faction) {
		return getDenizenUniqueIds(faction)
				.stream()
				.map(Bukkit::getPlayer)
				.filter(Objects::nonNull);
	}

	private EntityHelper() {
	}

}
