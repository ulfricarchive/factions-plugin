package com.ulfric.plugin.factions.function;

import com.ulfric.plugin.factions.model.dtr.DTR;

public class DtrNegativeFunction extends DtrFunction {

	public DtrNegativeFunction() {
		super("negative");
	}

	@Override
	public Object apply(DTR dtr) {
		return dtr.getNegativePoints();
	}

}
