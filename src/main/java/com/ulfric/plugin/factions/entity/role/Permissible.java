package com.ulfric.plugin.factions.entity.role;

import java.util.Set;

import com.ulfric.commons.naming.Named;

public interface Permissible extends Named {

	Set<String> getPermissions();

}
