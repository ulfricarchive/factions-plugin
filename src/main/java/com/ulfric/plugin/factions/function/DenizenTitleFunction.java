package com.ulfric.plugin.factions.function;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Denizen;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.role.StandardRoles;

public class DenizenTitleFunction extends DenizenFunction {

	@Inject
	private Factions factions;

	public DenizenTitleFunction() {
		super("title");
	}

	@Override
	public Object apply(Denizen denizen) {
		Faction faction = factions.getFaction(denizen.getFaction());
		Map<UUID, Set<String>> members = faction.getMembersToRoles();
		if (members == null) {
			return null;
		}

		Set<String> roles = members.get(UniqueIdHelper.parseUniqueIdExact(denizen.getIdentifier()));
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}

		return roles.stream()
			.map(StandardRoles::getByName)
			.filter(Objects::nonNull)
			.findFirst()
			.map(StandardRoles::getName)
			.orElse(null);
	}

}
