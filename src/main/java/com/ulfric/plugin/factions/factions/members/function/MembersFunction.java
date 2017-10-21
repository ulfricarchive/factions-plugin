package com.ulfric.plugin.factions.factions.members.function;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.Membership;

public class MembersFunction extends Function<Entity> {

	public MembersFunction() {
		super("members", Entity.class);
	}

	@Override
	public Object apply(Entity entity) {
		MembersComponent component = entity.getComponent(MembersComponent.KEY);

		if (component == null) {
			return null;
		}

		List<Membership> members = component.getSortedMembersList();
		if (CollectionUtils.isEmpty(members)) {
			return null;
		}

		return members;
	}

}
