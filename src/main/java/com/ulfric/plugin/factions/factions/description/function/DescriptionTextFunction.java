package com.ulfric.plugin.factions.factions.description.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

public class DescriptionTextFunction extends Function<DescriptionComponent> {

	public DescriptionTextFunction() {
		super("text", DescriptionComponent.class);
	}

	@Override
	public Object apply(DescriptionComponent description) {
		return description.getDescription();
	}

}
