package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.entity.Denizen;

public abstract class DenizenFunction extends Function<Denizen> {

	public DenizenFunction(String name) {
		super(name, Denizen.class);
	}

}
