package com.ulfric.plugin.factions.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ClassUtils;

import com.ulfric.commons.collection.Collectors2;
import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.stereotype.Stereotypes;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.CommandHelp;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.entity.role.PermissibleHelper;

@Name("factions")
@Alias({ "faction", "facs", "fac", "f", "team", "guild", "gang", "group" })
public class FactionsCommand extends CommandHelp {

	private static final Map<Class<?>, List<String>> REQUIRED_PERMISSIONS = new IdentityHashMap<>();

	protected final Factions factions = Factions.get();
	private Set<String> missingPermissions; // TODO refactor to use stages

	@Override
	public final void run() {
		List<String> permissions = getRequiredPermissions();

		if (!permissions.isEmpty()) {
			Set<String> user = getPermissions();

			missingPermissions = permissions.stream()
				.filter(permission -> !user.contains(permission))
				.collect(Collectors.toSet());

			if (!missingPermissions.isEmpty()) {
				if (missingPermissions.size() == 1) {
					tell("factions-missing-permission-single");
				} else {
					tell("factions-missing-permissions-multiple");
				}

				return;
			}
		}

		runFactionsCommand();
	}

	public void runFactionsCommand() {
		
	}

	private Set<String> getPermissions() { // TODO handle console execution
		return PermissibleHelper.getAllPermissions(uniqueId(), factions);
	}

	private List<String> getRequiredPermissions() {
		return REQUIRED_PERMISSIONS.computeIfAbsent(getClass(), type ->
			Stream.of(ClassUtils.getAllSuperclasses(type),
					ClassUtils.getAllInterfaces(type),
					Collections.singleton(type))
				.flatMap(Collection::stream)
				.distinct()
				.map(command -> Stereotypes.getAll(command, FactionPermission.class))
				.flatMap(Collection::stream)
				.map(FactionPermission::value)
				.collect(Collectors2.toUnmodifiableList()));
	}

}
