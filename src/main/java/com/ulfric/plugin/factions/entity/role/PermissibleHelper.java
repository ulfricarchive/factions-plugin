package com.ulfric.plugin.factions.entity.role;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.entity.Faction;

public class PermissibleHelper {

	public static Set<String> getAllPermissions(UUID uniqueId) {
		return getAllPermissions(uniqueId, Factions.get());
	}

	public static Set<String> getAllPermissions(UUID uniqueId, Factions factions) {
		String factionName = factions.getDenizen(uniqueId).getFaction();
		if (factionName == null) {
			return Collections.emptySet();
		}

		Faction faction = factions.getFaction(factionName);
		Map<UUID, Set<String>> membersToRoles = faction.getMembersToRoles();
		if (membersToRoles == null) {
			return Collections.emptySet();
		}

		Set<String> roles = membersToRoles.get(uniqueId);
		if (roles == null) {
			return Collections.emptySet();
		}

		Map<String, Permissible> existingRoles = new HashMap<>();
		existingRoles.putAll(StandardRoles.getAsMap());
		if (faction.getRoles() != null) {
			existingRoles.putAll(faction.getRoles());
		}

		return roles.stream()
			.map(String::toLowerCase)
			.map(existingRoles::get)
			.filter(Objects::nonNull)
			.map(Permissible::getPermissions)
			.flatMap(Set::stream)
			.collect(Collectors.toSet());
	}

	private PermissibleHelper() {
	}

}
