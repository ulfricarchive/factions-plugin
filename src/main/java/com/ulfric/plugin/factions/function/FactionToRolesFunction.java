package com.ulfric.plugin.factions.function;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.role.Role;

public class FactionToRolesFunction extends FactionFunction {

	public FactionToRolesFunction() {
		super("roles");
	}

	@Override
	public Object apply(Faction faction) {
		Map<String, Role> roles = faction.getRoles();

		if (MapUtils.isEmpty(roles)) {
			return null;
		}

		return roles.values();
	}

}
