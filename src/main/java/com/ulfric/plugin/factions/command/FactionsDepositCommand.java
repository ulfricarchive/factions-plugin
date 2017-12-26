package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.economy.Economy;

@Name("deposit")
@FactionPermission("deposit")
public class FactionsDepositCommand extends FactionFactionsCommand {

	@Inject
	private Economy economy;

	@Override
	public void run() {
		
	}

}
