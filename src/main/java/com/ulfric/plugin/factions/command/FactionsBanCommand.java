package com.ulfric.plugin.factions.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.plugin.factions.model.ban.Ban;

@Name("ban")
@FactionPermission("ban")
public class FactionsBanCommand extends SkeletalFactionsKickCommand {

	private String reason;

	@Override
	public void run() {
		if (isSelfTarget()) {
			tell("factions-ban-self");
			return;
		}

		if (isTargetInSameFaction()) {
			if (!senderHasHigherRank()) {
				tell("factions-ban-rank-too-low"); // TODO should we send out other notifications to the faction?
				return;
			}

			removeLink();
		} else {
			
		}

		UUID targetUniqueId = targetUniqueId();
		List<Ban> bans = faction.getBans();
		if (CollectionUtils.isEmpty(bans)) {
			bans = new ArrayList<>();
		} else {
			for (Ban ban : bans) {
				if (Objects.equals(ban.getTarget(), targetUniqueId)) {
					tell("factions-ban-already-banned");
					return;
				}
			}
		}

		setupReason();

		Ban ban = new Ban();
		ban.setCreation(TemporalHelper.instantNow());
		ban.setReason(reason);
		ban.setSender(uniqueId());
		ban.setTarget(targetUniqueId);
		bans.add(ban);
		faction.setBans(bans);

		persistTargetAndFaction();

		notifyInvolvedParties();
	}

	private void notifyInvolvedParties() {
		tellDenizensExceptForSender("factions-ban-by");
		tell("factions-ban");
		tellTarget("factions-ban-to");
	}

	private void setupReason() {
		List<String> arguments = context.getArguments()
				.getArguments()
				.get(FactionsBanCommand.class)
				.getArguments();

		if (CollectionUtils.isEmpty(arguments)) {
			return;
		}

		reason = arguments.stream()
				.collect(Collectors.joining(" "));
	}

}
