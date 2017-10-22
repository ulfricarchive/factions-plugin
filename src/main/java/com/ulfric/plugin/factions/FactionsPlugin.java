package com.ulfric.plugin.factions;

import com.ulfric.i18n.function.Function;
import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.entities.components.ComponentKeys;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.factions.command.FactionsCreateSystemCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionColorCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;
import com.ulfric.plugin.factions.command.FactionsDisbandCommand;
import com.ulfric.plugin.factions.command.FactionsHelpCommand;
import com.ulfric.plugin.factions.command.FactionsInviteCommand;
import com.ulfric.plugin.factions.command.FactionsInvitesCommand;
import com.ulfric.plugin.factions.command.FactionsKickCommand;
import com.ulfric.plugin.factions.command.FactionsLeaveCommand;
import com.ulfric.plugin.factions.command.FactionsShowCommand;
import com.ulfric.plugin.factions.command.FactionsUninviteCommand;
import com.ulfric.plugin.factions.command.argument.DenizenResolver;
import com.ulfric.plugin.factions.command.argument.FactionResolver;
import com.ulfric.plugin.factions.denizens.membership.MembershipComponent;
import com.ulfric.plugin.factions.factions.description.DescriptionComponent;
import com.ulfric.plugin.factions.factions.description.function.DescriptionColorFunction;
import com.ulfric.plugin.factions.factions.description.function.DescriptionFunction;
import com.ulfric.plugin.factions.factions.description.function.DescriptionTextFunction;
import com.ulfric.plugin.factions.factions.invitations.InvitationsComponent;
import com.ulfric.plugin.factions.factions.invitations.function.InvitationDateFunction;
import com.ulfric.plugin.factions.factions.invitations.function.InvitationInvitedFunction;
import com.ulfric.plugin.factions.factions.invitations.function.InvitationInviterFunction;
import com.ulfric.plugin.factions.factions.invitations.function.InvitationsFunction;
import com.ulfric.plugin.factions.factions.members.MembersComponent;
import com.ulfric.plugin.factions.factions.members.function.MemberJoinedFunction;
import com.ulfric.plugin.factions.factions.members.function.MemberNameFunction;
import com.ulfric.plugin.factions.factions.members.function.MemberRolesFunction;
import com.ulfric.plugin.factions.factions.members.function.MembersFunction;
import com.ulfric.plugin.factions.factions.roles.RolesComponent;
import com.ulfric.plugin.factions.factions.score.ScoreComponent;
import com.ulfric.plugin.factions.factions.system.SystemComponent;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);

		install(DenizenResolver.class);
		install(FactionResolver.class);

		install(FactionsCommand.class);
		install(FactionsHelpCommand.class);
		install(FactionsCreateCommand.class);
		install(FactionsCreateSystemCommand.class);
		install(FactionsDescriptionCommand.class);
		install(FactionsDescriptionColorCommand.class);
		install(FactionsShowCommand.class);
		install(FactionsDisbandCommand.class);
		install(FactionsInviteCommand.class);
		install(FactionsInvitesCommand.class);
		install(FactionsUninviteCommand.class);
		install(FactionsKickCommand.class);
		install(FactionsLeaveCommand.class);

		// TODO use feature wrappers
		ComponentKeys.register(DescriptionComponent.KEY);
		ComponentKeys.register(MembershipComponent.KEY);
		ComponentKeys.register(MembersComponent.KEY);
		ComponentKeys.register(RolesComponent.KEY);
		ComponentKeys.register(SystemComponent.KEY);
		ComponentKeys.register(InvitationsComponent.KEY);
		ComponentKeys.register(ScoreComponent.KEY);

		Function.register(new DescriptionFunction());
		Function.register(new DescriptionTextFunction());
		Function.register(new DescriptionColorFunction());
		Function.register(new MembersFunction());
		Function.register(new MemberNameFunction());
		Function.register(new MemberRolesFunction());
		Function.register(new MemberJoinedFunction());
		Function.register(new InvitationsFunction());
		Function.register(new InvitationDateFunction());
		Function.register(new InvitationInviterFunction());
		Function.register(new InvitationInvitedFunction());
	}

}
