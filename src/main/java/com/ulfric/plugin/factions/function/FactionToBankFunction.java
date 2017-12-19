package com.ulfric.plugin.factions.function;

import java.util.UUID;

import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Faction;

public class FactionToBankFunction extends FactionFunction {

	@Inject
	private Factions factions;

	@Inject
	private Economy economy;

	public FactionToBankFunction() {
		super("bank");
	}

	@Override
	public Object apply(Faction faction) {
		UUID accountIdentifier = faction.getBankAccount();

		if (accountIdentifier == null) {
			return null;
		}

		return economy.getBankAccount(accountIdentifier);
	}

}
