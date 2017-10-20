package com.ulfric.plugin.factions.factions.description.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.description.DescriptionColor;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

public class DescriptionColorFunction extends Function<DescriptionComponent> {

	public DescriptionColorFunction() {
		super("color", DescriptionComponent.class);
	}

	@Override
	public Object apply(DescriptionComponent description) {
		DescriptionColor color = description.getColor();

		if (color == null) {
			return null;
		}

		return color.getChatColor();
	}

}
