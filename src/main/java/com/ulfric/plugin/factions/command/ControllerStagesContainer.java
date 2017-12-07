package com.ulfric.plugin.factions.command;

import com.ulfric.broken.ErrorHandler;
import com.ulfric.broken.StandardCriteria;
import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.extension.postconstruct.PostConstruct;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.exception.CommandChannel;
import com.ulfric.plugin.locale.TellService;

public class ControllerStagesContainer extends Container {

	@Inject
	@CommandChannel
	private ErrorHandler errorHandler;

	public ControllerStagesContainer() {
		install(DenizenStage.class);
		install(FactionStage.class);
		install(FactionPermissionsStage.class);
	}

	@PostConstruct
	private void setupErrorHandler() {
		errorHandler.withHandler(FactionRequiredException.class).setCriteria(StandardCriteria.EXACT_TYPE_MATCH)
				.setAction(factionRequired -> TellService.sendMessage(factionRequired.getContext().getSender(),
						"factions-must-be-in-faction"))
				.add();

		errorHandler.withHandler(FactionMissingPermissionException.class).setCriteria(StandardCriteria.EXACT_TYPE_MATCH)
				.setAction(missingPermission -> TellService.sendMessage(missingPermission.getContext().getSender(),
						"factions-missing-permission",
						Details.of("permission", missingPermission.getMissingPermission())))
				.add();
	}

}
