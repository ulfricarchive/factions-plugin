package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.command.ControllerStagesContainer;
import com.ulfric.plugin.factions.command.DenizenResolver;
import com.ulfric.plugin.factions.command.FactionResolver;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;
import com.ulfric.plugin.factions.command.FactionsDisbandCommand;
import com.ulfric.plugin.factions.command.FactionsInviteCommand;
import com.ulfric.plugin.factions.command.FactionsInvitesCommand;
import com.ulfric.plugin.factions.command.FactionsJoinCommand;
import com.ulfric.plugin.factions.command.FactionsLeaveCommand;
import com.ulfric.plugin.factions.command.FactionsRoleCommand;
import com.ulfric.plugin.factions.command.FactionsRolesCommand;
import com.ulfric.plugin.factions.command.FactionsUninviteCommand;
import com.ulfric.plugin.factions.function.DenizenNameFunction;
import com.ulfric.plugin.factions.function.FactionDescriptionFunction;
import com.ulfric.plugin.factions.function.FactionInvitesFunction;
import com.ulfric.plugin.factions.function.FactionNameFunction;
import com.ulfric.plugin.factions.function.FactionRolesFunction;
import com.ulfric.plugin.factions.function.InvitationCreationFunction;
import com.ulfric.plugin.factions.function.InvitationInvitedFunction;
import com.ulfric.plugin.factions.function.InvitationInviterFunction;
import com.ulfric.plugin.factions.function.RoleNameFunction;
import com.ulfric.plugin.factions.function.RolePermissionsFunction;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);

		install(ControllerStagesContainer.class);

		install(FactionNameFunction.class);
		install(FactionDescriptionFunction.class);
		install(FactionRolesFunction.class);
		install(FactionInvitesFunction.class);
		install(DenizenNameFunction.class);
		install(RoleNameFunction.class);
		install(RolePermissionsFunction.class);
		install(InvitationCreationFunction.class);
		install(InvitationInvitedFunction.class);
		install(InvitationInviterFunction.class);

		install(FactionsCommand.class);
		install(FactionsCreateCommand.class);
		install(FactionsDisbandCommand.class);
		install(FactionsDescriptionCommand.class);
		install(FactionsRoleCommand.class);
		install(FactionsRolesCommand.class);
		install(FactionsUninviteCommand.class);
		install(FactionsInviteCommand.class);
		install(FactionsInvitesCommand.class);
		install(FactionsJoinCommand.class);
		install(FactionsLeaveCommand.class);

		install(DenizenResolver.class);
		install(FactionResolver.class);
	}

}
