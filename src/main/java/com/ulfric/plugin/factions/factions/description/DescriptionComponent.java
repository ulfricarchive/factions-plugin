package com.ulfric.plugin.factions.factions.description;

import java.util.UUID;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class DescriptionComponent extends Component {

	public static final ComponentKey<DescriptionComponent> KEY = DescriptionComponentKey.INSTANCE;

	private String description;
	private UUID writtenBy;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getWrittenBy() {
		return writtenBy;
	}

	public void setWrittenBy(UUID writtenBy) {
		this.writtenBy = writtenBy;
	}

}
