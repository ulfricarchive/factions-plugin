package com.ulfric.plugin.factions.command;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.value.UniqueIdHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.CommandHelp;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Denizen;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.role.StandardRoles;

@Name("factions")
@Alias({ "faction", "facs", "fac", "f", "team", "guild", "gang", "group" })
public class FactionsCommand extends CommandHelp {

	@Inject
	protected Factions factions;

	protected Denizen denizen;

	protected void tell(Denizen denizen, String message) {
		Player player = Bukkit.getPlayer(UniqueIdHelper.parseUniqueIdExact(denizen.getIdentifier()));
		if (player != null) {
			tell(player, message);
		}
	}

	protected int permissionLevel() {
		return calculatePermissionLevel(denizen);
	}

	protected int calculatePermissionLevel(Denizen denizen) {
		String factionName = denizen.getFaction();
		if (StringUtils.isEmpty(factionName)) {
			return 0;
		}

		Faction faction = factions.getFaction(factionName);
		Map<UUID, Set<String>> membersToRoles = faction.getMembersToRoles();
		if (MapUtils.isEmpty(membersToRoles)) {
			return 0;
		}

		Set<String> roles = membersToRoles.get(UniqueIdHelper.parseUniqueIdExact(denizen.getIdentifier()));
		if (CollectionUtils.isEmpty(roles)) {
			return 0;
		}

		return roles.stream()
				.map(StandardRoles::getByName)
				.filter(Objects::nonNull)
				.sorted()
				.findFirst()
				.map(StandardRoles::ordinal)
				.orElse(0);
	}

}
