package com.ulfric.plugin.factions.factions.description.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;

public class DescriptionFunction extends Function<Entity> {

	public DescriptionFunction() {
		super("description", Entity.class);
	}

	@Override
	public Object apply(Entity entity) {
		return entity.getComponent(DescriptionComponent.KEY);
	}

}
