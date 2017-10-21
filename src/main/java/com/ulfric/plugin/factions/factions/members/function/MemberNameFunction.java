package com.ulfric.plugin.factions.factions.members.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.members.Membership;

public class MemberNameFunction extends Function<Membership> {

	public MemberNameFunction() {
		super("name", Membership.class);
	}

	@Override
	public Object apply(Membership membership) {
		return membership.getName();
	}

}
