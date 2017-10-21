package com.ulfric.plugin.factions.factions.members.function;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.factions.factions.members.Membership;

public class MemberJoinedFunction extends Function<Membership> {

	public MemberJoinedFunction() {
		super("joined", Membership.class);
	}

	@Override
	public Object apply(Membership membership) {
		return membership.getJoined();
	}

}
