package com.ulfric.plugin.factions.factions.score;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.entities.components.ComponentKey;

@Name("factions/score")
enum ScoreComponentKey implements ComponentKey<ScoreComponent> {

	INSTANCE;

	@Override
	public Class<ScoreComponent> getComponentType() {
		return ScoreComponent.class;
	}

}
