package com.ulfric.plugin.factions.factions.description;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class DescriptionComponent extends Component {

	public static final ComponentKey<DescriptionComponent> KEY = DescriptionComponentKey.INSTANCE;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
