package com.ulfric.plugin.factions.factions.members.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.members.Membership;

public class MemberRolesFunction extends Function<Membership> {

	public MemberRolesFunction() {
		super("roles", Membership.class);
	}

	@Override
	public Object apply(Membership membership) {
		return membership.getRoles();
	}

}
