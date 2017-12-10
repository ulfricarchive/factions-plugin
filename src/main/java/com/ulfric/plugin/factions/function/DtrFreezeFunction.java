package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.dtr.DTR;

public class DtrFreezeFunction extends DtrFunction {

	public DtrFreezeFunction() {
		super("freeze");
	}

	@Override
	public Object apply(DTR dtr) {
		return dtr.getFreezeExpiration();
	}

}
