package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.entities.components.ComponentKeys;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;
import com.ulfric.plugin.factions.factions.members.MembersComponent;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);

		install(FactionsCommand.class);
		install(FactionsDescriptionCommand.class);
		install(FactionsCreateCommand.class);

		// TODO use feature wrappers
		ComponentKeys.register(DescriptionComponent.KEY);
		ComponentKeys.register(MembershipComponent.KEY);
		ComponentKeys.register(MembersComponent.KEY);
	}

}
