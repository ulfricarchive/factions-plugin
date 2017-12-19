package com.ulfric.plugin.factions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.factions.command.ControllerStagesContainer;
import com.ulfric.plugin.factions.command.DenizenResolver;
import com.ulfric.plugin.factions.command.FactionResolver;
import com.ulfric.plugin.factions.command.FactionsBanCommand;
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
import com.ulfric.plugin.factions.function.DenizenToNameFunction;
import com.ulfric.plugin.factions.function.DenizenToTitleFunction;
import com.ulfric.plugin.factions.function.DtrToFreezeFunction;
import com.ulfric.plugin.factions.function.DtrToNegativeFunction;
import com.ulfric.plugin.factions.function.FactionToBankFunction;
import com.ulfric.plugin.factions.function.FactionToDenizensFunction;
import com.ulfric.plugin.factions.function.FactionToDescriptionFunction;
import com.ulfric.plugin.factions.function.FactionToDtrFunction;
import com.ulfric.plugin.factions.function.FactionToDtrMetaFunction;
import com.ulfric.plugin.factions.function.FactionToHomeFunction;
import com.ulfric.plugin.factions.function.FactionToInvitesFunction;
import com.ulfric.plugin.factions.function.FactionToNameFunction;
import com.ulfric.plugin.factions.function.FactionToRaidableFunction;
import com.ulfric.plugin.factions.function.FactionToRolesFunction;
import com.ulfric.plugin.factions.function.InvitationToCreationFunction;
import com.ulfric.plugin.factions.function.InvitationToInvitedFunction;
import com.ulfric.plugin.factions.function.InvitationToInviterFunction;
import com.ulfric.plugin.factions.function.RoleToNameFunction;
import com.ulfric.plugin.factions.function.RoleToPermissionsFunction;
import com.ulfric.plugin.factions.model.dtr.RaidHelper;

public class FactionsPlugin extends Plugin {

	public FactionsPlugin() {
		install(Factions.class);
		install(RaidHelper.class);

		install(ControllerStagesContainer.class);

		install(FactionToNameFunction.class);
		install(FactionToDescriptionFunction.class);
		install(FactionToRolesFunction.class);
		install(FactionToInvitesFunction.class);
		install(FactionToDtrMetaFunction.class);
		install(FactionToDtrFunction.class);
		install(FactionToRaidableFunction.class);
		install(DtrToFreezeFunction.class);
		install(DtrToNegativeFunction.class);
		install(DenizenToNameFunction.class);
		install(RoleToNameFunction.class);
		install(RoleToPermissionsFunction.class);
		install(InvitationToCreationFunction.class);
		install(InvitationToInvitedFunction.class);
		install(InvitationToInviterFunction.class);
		install(FactionToDenizensFunction.class);
		install(DenizenToTitleFunction.class);
		install(FactionToHomeFunction.class);
		install(FactionToBankFunction.class);

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
		install(FactionsBanCommand.class);

		install(DtrListener.class);
		install(DtrRegenTask.class);
	}

}
