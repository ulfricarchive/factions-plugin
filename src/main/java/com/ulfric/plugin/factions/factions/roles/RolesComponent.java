package com.ulfric.plugin.factions.factions.roles;

import java.util.Map;
import java.util.Set;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class RolesComponent extends Component {
	
	public static final ComponentKey<RolesComponent> KEY = RolesComponentKey.INSTANCE;

	private Map<String, Role> roles;
	private Set<StandardRoles> hidden;

	public Map<String, Role> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, Role> roles) {
		this.roles = roles;
	}

	public Set<StandardRoles> getHidden() {
		return hidden;
	}

	public void setHidden(Set<StandardRoles> hidden) {
		this.hidden = hidden;
	}

	public Permissible getPermissibleByName(String name) {
		Map<String, Role> roles = getRoles();
		if (roles == null) {
			return null;
		}

		Role role = roles.get(name.toLowerCase());
		if (role != null) {
			return role;
		}

		StandardRoles standardRole = StandardRoles.getByName(name);
		if (standardRole == null) {
			return null;
		}

		Set<StandardRoles> ignoredRoles = getHidden();
		if (ignoredRoles != null && ignoredRoles.contains(standardRole)) {
			return null;
		}

		return standardRole;
	}

}
