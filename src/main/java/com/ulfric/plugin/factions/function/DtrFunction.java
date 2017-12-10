package com.ulfric.plugin.factions.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.model.dtr.DTR;

public abstract class DtrFunction extends Function<DTR> {

	public DtrFunction(String name) {
		super(name, DTR.class);
	}

}
