package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.dtr.DTR;

public class DtrToNegativeFunction extends DtrFunction {

	public DtrToNegativeFunction() {
		super("negative");
	}

	@Override
	public Object apply(DTR dtr) {
		return dtr.getNegativePoints();
	}

}
