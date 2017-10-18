package com.ulfric.plugin.factions.factions.roles;

import java.util.List;
import java.util.Set;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class RolesComponent extends Component {
	
	public static final ComponentKey<RolesComponent> KEY = RolesComponentKey.INSTANCE;

	private List<Role> roles;
	private Set<StandardRoles> hidden;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Set<StandardRoles> getHidden() {
		return hidden;
	}

	public void setHidden(Set<StandardRoles> hidden) {
		this.hidden = hidden;
	}

}
