package com.ulfric.plugin.factions.factions.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.util.CollectionUtils;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class ScoreComponent extends Component {

	public static final ComponentKey<ScoreComponent> KEY = ScoreComponentKey.INSTANCE;

	private List<Event> events;

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public void addEvent(Event event) {
		Objects.requireNonNull(event, "event");

		List<Event> events = getEvents();
		if (events == null) {
			setEvents(new ArrayList<>());
			events = getEvents();
		}

		events.add(event);
	}

	public int getScore() {
		if (CollectionUtils.isEmpty(events)) {
			return 0;
		}

		return events.stream()
				.map(Event::getScore)
				.filter(Objects::nonNull)
				.mapToInt(Integer::intValue)
				.sum();
	}

}
