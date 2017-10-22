package com.ulfric.plugin.factions.factions.description;

import java.time.Instant;
import java.util.UUID;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class DescriptionComponent extends Component {

	public static ComponentKey<DescriptionComponent> getKey() {
		return KEY;
	}

	public static final ComponentKey<DescriptionComponent> KEY = DescriptionComponentKey.INSTANCE;

	private String description;
	private DescriptionColor color;
	private UUID author;
	private Instant dateWritten;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DescriptionColor getColor() {
		return color;
	}

	public void setColor(DescriptionColor color) {
		this.color = color;
	}

	public UUID getAuthor() {
		return author;
	}

	public void setAuthor(UUID author) {
		this.author = author;
	}

	public Instant getDateWritten() {
		return dateWritten;
	}

	public void setDateWritten(Instant dateWritten) {
		this.dateWritten = dateWritten;
	}

}
