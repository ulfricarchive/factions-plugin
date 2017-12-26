package com.ulfric.plugin.factions.command;

import java.math.BigDecimal;
import java.util.UUID;

import com.ulfric.commons.naming.Name;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.economy.BankAccount;
import com.ulfric.plugin.economy.Economy;
import com.ulfric.plugin.economy.command.SkeletalPayCommand;

@Name("deposit")
@FactionPermission("deposit")
public class FactionsDepositCommand extends FactionFactionsCommand implements SkeletalPayCommand {

	@Inject
	private Economy economy;

	@Argument
	private BigDecimal amount;

	private BankAccount account;

	@Override
	public void run() {
		pay();
	}

	@Override
	public Economy economy() {
		return economy;
	}

	@Override
	public BigDecimal amount() {
		return amount;
	}

	@Override
	public BankAccount target() {
		if (account == null) {
			UUID accountId = faction.getBankAccount();
			if (accountId == null) {
				accountId = UUID.randomUUID();
				faction.setBankAccount(accountId);
				persistFaction();
			}
			account = economy.getBankAccount(accountId);
		}
		return account;
	}

	@Override
	public String reason() {
		return "/f deposit";
	}

	@Override
	public void onPayTarget() {
		tell("factions-deposit");
	}

	@Override
	public void onPaidBySender() {
		tellDenizensExceptForSender("factions-deposit-by");
	}

	@Override
	public void onPaymentFail() {
		tell("factions-deposit-fail");
	}

	@Override
	public void onInvalidAmount() {
		tell("factions-deposit-invaild-amount");
	}

}
