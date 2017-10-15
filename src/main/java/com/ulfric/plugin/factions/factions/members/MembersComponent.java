package com.ulfric.plugin.factions.factions.members;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class MembersComponent extends Component {
	
	public static final ComponentKey<MembersComponent> KEY = MembersComponentKey.INSTANCE;

	private Map<UUID, List<Position>> members;

	public Map<UUID, List<Position>> getMembers() {
		return members;
	}

	public void setMembers(Map<UUID, List<Position>> members) {
		this.members = members;
	}

}
