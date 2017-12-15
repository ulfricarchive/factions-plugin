package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;

@Name("kick")
@FactionPermission("kick")
public class FactionsKickCommand extends SkeletalFactionsKickCommand {

	@Override
	public void run() {
		if (isSelfTarget()) {
			tell("factions-kick-self");
			return;
		}

		if (!isTargetInSameFaction()) {
			tell("factions-kick-not-member");
			return;
		}

		if (!senderHasHigherRank()) {
			tell("factions-kick-rank-too-low");
			return;
		}

		removeLink();
		persistTargetAndFaction();

		notifyInvolvedParties();
	}

	private void notifyInvolvedParties() {
		tellDenizensExceptForSender("factions-kick-by");
		tell("factions-kick");
		tellTarget("factions-kick-to");
	}

}
