package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.command.ControllerStagesContainer;
import com.ulfric.plugin.factions.command.DenizenResolver;
import com.ulfric.plugin.factions.command.FactionResolver;
import com.ulfric.plugin.factions.command.FactionsCloseCommand;
import com.ulfric.plugin.factions.command.FactionsCommand;
import com.ulfric.plugin.factions.command.FactionsCreateCommand;
import com.ulfric.plugin.factions.command.FactionsDescriptionCommand;
import com.ulfric.plugin.factions.command.FactionsDisbandCommand;
import com.ulfric.plugin.factions.command.FactionsInviteCommand;
import com.ulfric.plugin.factions.command.FactionsInvitesCommand;
import com.ulfric.plugin.factions.command.FactionsJoinCommand;
import com.ulfric.plugin.factions.command.FactionsKickCommand;
import com.ulfric.plugin.factions.command.FactionsLeaveCommand;
import com.ulfric.plugin.factions.command.FactionsOpenCommand;
import com.ulfric.plugin.factions.command.FactionsRoleCommand;
import com.ulfric.plugin.factions.command.FactionsRolesCommand;
import com.ulfric.plugin.factions.command.FactionsShowCommand;
import com.ulfric.plugin.factions.command.FactionsShowCountCommand;
import com.ulfric.plugin.factions.command.FactionsUninviteCommand;
import com.ulfric.plugin.factions.dtr.DtrListener;
import com.ulfric.plugin.factions.dtr.DtrRegenTask;
import com.ulfric.plugin.factions.function.DenizenNameFunction;
import com.ulfric.plugin.factions.function.DenizenTitleFunction;
import com.ulfric.plugin.factions.function.DtrFreezeFunction;
import com.ulfric.plugin.factions.function.DtrNegativeFunction;
import com.ulfric.plugin.factions.function.FactionDenizensFunction;
import com.ulfric.plugin.factions.function.FactionDescriptionFunction;
import com.ulfric.plugin.factions.function.FactionDtrFunction;
import com.ulfric.plugin.factions.function.FactionDtrMetaFunction;
import com.ulfric.plugin.factions.function.FactionHomeFunction;
import com.ulfric.plugin.factions.function.FactionInvitesFunction;
import com.ulfric.plugin.factions.function.FactionNameFunction;
import com.ulfric.plugin.factions.function.FactionRaidableFunction;
import com.ulfric.plugin.factions.function.FactionRolesFunction;
import com.ulfric.plugin.factions.function.InvitationCreationFunction;
import com.ulfric.plugin.factions.function.InvitationInvitedFunction;
import com.ulfric.plugin.factions.function.InvitationInviterFunction;
import com.ulfric.plugin.factions.function.RoleNameFunction;
import com.ulfric.plugin.factions.function.RolePermissionsFunction;
import com.ulfric.plugin.factions.model.dtr.RaidHelper;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);
		install(RaidHelper.class);

		install(ControllerStagesContainer.class);

		install(FactionNameFunction.class);
		install(FactionDescriptionFunction.class);
		install(FactionRolesFunction.class);
		install(FactionInvitesFunction.class);
		install(FactionDtrMetaFunction.class);
		install(FactionDtrFunction.class);
		install(FactionRaidableFunction.class);
		install(DtrFreezeFunction.class);
		install(DtrNegativeFunction.class);
		install(DenizenNameFunction.class);
		install(RoleNameFunction.class);
		install(RolePermissionsFunction.class);
		install(InvitationCreationFunction.class);
		install(InvitationInvitedFunction.class);
		install(InvitationInviterFunction.class);
		install(FactionDenizensFunction.class);
		install(DenizenTitleFunction.class);
		install(FactionHomeFunction.class);

		install(DenizenResolver.class);
		install(FactionResolver.class);

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
		install(FactionsOpenCommand.class);
		install(FactionsCloseCommand.class);
		install(FactionsShowCommand.class);
		install(FactionsShowCountCommand.class);
		install(FactionsKickCommand.class);

		install(DtrListener.class);
		install(DtrRegenTask.class);
	}

}
