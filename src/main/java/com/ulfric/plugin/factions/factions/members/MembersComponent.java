package com.ulfric.plugin.factions.factions.members;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;
import com.ulfric.plugin.factions.factions.roles.AggregatePermissible;
import com.ulfric.plugin.factions.factions.roles.EmptyPermissible;
import com.ulfric.plugin.factions.factions.roles.Permissible;
import com.ulfric.plugin.factions.factions.roles.RolesComponent;

public class MembersComponent extends Component {
	
	public static final ComponentKey<MembersComponent> KEY = MembersComponentKey.INSTANCE;

	public static Permissible getPermissions(Entity entity, UUID member) {
		if (entity == null || member == null) {
			return EmptyPermissible.INSTANCE;
		}

		MembersComponent members = entity.getComponent(MembersComponent.KEY);
		if (members == null) {
			return EmptyPermissible.INSTANCE;
		}

		RolesComponent roles = entity.getComponent(RolesComponent.KEY);
		if (roles == null) {
			return EmptyPermissible.INSTANCE;
		}

		List<Permissible> permissions = members.getPositions(member)
				.stream()
				.map(Position::getRole)
				.filter(Objects::nonNull)
				.map(roles::getPermissibleByName)
				.collect(Collectors.toList());

		return permissions.isEmpty() ? EmptyPermissible.INSTANCE : new AggregatePermissible(permissions);
	}

	private Map<UUID, List<Position>> members;

	public Map<UUID, List<Position>> getMembers() {
		return members;
	}

	public void setMembers(Map<UUID, List<Position>> members) {
		this.members = members;
	}

	public List<Position> getPositions(UUID member) {
		Map<UUID, List<Position>> members = getMembers();

		if (members == null) {
			return Collections.emptyList();
		}

		return members.getOrDefault(member, Collections.emptyList());
	}

}
