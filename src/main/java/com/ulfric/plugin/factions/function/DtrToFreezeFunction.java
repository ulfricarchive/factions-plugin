package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.dtr.DTR;

public class DtrToFreezeFunction extends DtrFunction {

	public DtrToFreezeFunction() {
		super("freeze");
	}

	@Override
	public Object apply(DTR dtr) {
		return dtr.getFreezeExpiration();
	}

}
