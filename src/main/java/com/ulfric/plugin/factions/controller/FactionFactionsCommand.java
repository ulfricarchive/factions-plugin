package com.ulfric.plugin.factions.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ulfric.plugin.factions.entity.Denizen;
import com.ulfric.plugin.factions.entity.Faction;

public abstract class FactionFactionsCommand extends FactionsCommand {

	protected Faction faction;

	@Override
	public final void runFactionsCommand() {
		String factionName = factions.getDenizen(player()).getFaction();
		if (factionName == null) {
			tell("factions-must-be-in-faction");
			return;
		}

		faction = factions.getFaction(factionName);
		runCommandWithFaction();
	}

	public abstract void runCommandWithFaction();

	protected Set<UUID> getDenizenUniqueIds() {
		Map<UUID, Set<String>> members = faction.getMembersToRoles();

		if (members == null) {
			return Collections.emptySet();
		}

		return members.keySet();
	}

	protected Stream<Denizen> getDenizens() {
		return getDenizenUniqueIds()
				.stream()
				.map(factions::getDenizen);
	}

	protected Stream<Player> getOnlineDenizens() {
		return getDenizenUniqueIds()
				.stream()
				.map(Bukkit::getPlayer)
				.filter(Objects::nonNull);
	}

	protected void tellDenizensExceptForSender(String message) {
		Player player = player();
		getOnlineDenizens()
			.filter(player::equals)
			.forEach(denizen -> tell(denizen, message));
	}

}
