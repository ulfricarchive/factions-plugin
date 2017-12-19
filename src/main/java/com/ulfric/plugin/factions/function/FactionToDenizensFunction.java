package com.ulfric.plugin.factions.function;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;

public class FactionToDenizensFunction extends FactionFunction {

	@Inject
	private Factions factions;

	public FactionToDenizensFunction() {
		super("denizens");
	}

	@Override
	public Object apply(Faction faction) {
		Map<UUID, Set<String>> membersToRoles = faction.getMembersToRoles();
		if (MapUtils.isEmpty(membersToRoles)) {
			return null;
		}

		return EntityHelper.getDenizens(faction);
	}

}
