package com.ulfric.plugin.factions.factions.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.factions.roles.AggregatePermissible;
import com.ulfric.plugin.factions.factions.roles.EmptyPermissible;
import com.ulfric.plugin.factions.factions.roles.Permissible;
import com.ulfric.plugin.factions.factions.roles.RolesComponent;

public class MembersComponent extends Component {
	
	public static final ComponentKey<MembersComponent> KEY = MembersComponentKey.INSTANCE;

	public static List<UUID> getLeaders(Entity faction) { // TODO cleanup
		if (faction == null) {
			return Collections.emptyList();
		}

		MembersComponent members = faction.getComponent(MembersComponent.KEY);
		if (members == null || members.members == null) {
			return Collections.emptyList();
		}

		Map<Integer, List<UUID>> rankToMembers = new HashMap<>();
		members.members.keySet().forEach(member ->
			rankToMembers.computeIfAbsent(getPermissions(faction, member).getWorth(),
					ignore -> new ArrayList<>()).add(member));

		int highestRank = rankToMembers.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
		List<UUID> highest = rankToMembers.get(highestRank);
		return highest == null ? Collections.emptyList() : highest;
	}

	public static Permissible getPermissions(Entity faction, UUID member) { // TODO cleanup
		if (faction == null || member == null) {
			return EmptyPermissible.INSTANCE;
		}

		MembersComponent members = faction.getComponent(MembersComponent.KEY);
		if (members == null) {
			return EmptyPermissible.INSTANCE;
		}

		RolesComponent roles = faction.getComponent(RolesComponent.KEY);
		if (roles == null) {
			return EmptyPermissible.INSTANCE;
		}

		Membership membership = members.getMembership(member);
		if (membership == null) {
			return EmptyPermissible.INSTANCE;
		}

		List<String> memberRoles = membership.getRoles();
		if (CollectionUtils.isEmpty(memberRoles)) {
			return EmptyPermissible.INSTANCE;
		}

		List<Permissible> permissions = memberRoles.stream()
				.filter(Objects::nonNull)
				.map(roles::getPermissibleByName)
				.collect(Collectors.toList());

		if (permissions.isEmpty()) {
			return EmptyPermissible.INSTANCE;
		}

		if (permissions.size() == 1) {
			return permissions.get(0);
		}

		return new AggregatePermissible(permissions);
	}

	private Map<UUID, Membership> members;

	public Map<UUID, Membership> getMembers() {
		return members;
	}

	public void setMembers(Map<UUID, Membership> members) {
		this.members = members;
	}

	public Membership getMembership(UUID member) {
		Map<UUID, Membership> members = getMembers();

		if (members == null) {
			return null;
		}

		return members.get(member);
	}

	public List<Membership> getSortedMembersList() {
		Map<UUID, Membership> members = getMembers();
		if (MapUtils.isEmpty(members)) {
			return Collections.emptyList();
		}
		List<Membership> sorted = new ArrayList<>(members.values());
		Collections.sort(sorted);
		return sorted;
	}

	public Set<UUID> getMembersSet() {
		Map<UUID, Membership> members = getMembers();
		if (MapUtils.isEmpty(members)) {
			return Collections.emptySet();
		}
		return members.keySet();
	}

	public List<Entity> getMemberEntities(Factions factions) {
		return getMembersSet()
				.stream()
				.map(factions::getDenizenByUniqueId)
				.filter(Objects::nonNull)
				.map(CompletableFuture::join)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

}
