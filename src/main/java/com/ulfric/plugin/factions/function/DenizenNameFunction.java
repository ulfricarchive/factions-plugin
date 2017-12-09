package com.ulfric.plugin.factions.function;

import java.util.UUID;

import com.ulfric.commons.bukkit.player.PlayerHelper;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.plugin.factions.model.Denizen;

public class DenizenNameFunction extends DenizenFunction {

	public DenizenNameFunction() {
		super("name");
	}

	@Override
	public Object apply(Denizen denizen) {
		UUID uniqueId = UniqueIdHelper.parseUniqueIdExact(denizen.getIdentifier());

		if (uniqueId == null) {
			return null;
		}

		return PlayerHelper.getName(uniqueId);
	}

}
