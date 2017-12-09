package com.ulfric.plugin.factions.dtr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.dtr.DTR;
import com.ulfric.plugin.locale.TellService;
import com.ulfric.plugin.tasks.Scheduler;
import com.ulfric.plugin.tasks.Task;

public class DtrRegenTask extends Container {

	@Inject
	private Scheduler scheduler;

	@Inject
	private DtrTask runnable;

	@Inject
	private Factions factions;

	private Task task;

	public DtrRegenTask() {
		addBootHook(this::startTask);
		addShutdownHook(this::stopTask);
	}

	private void startTask() {
		Duration frequency = Duration.parse(factions.settings().dtrRegenFrequency());
		task = scheduler.runOnMainThreadRepeating(runnable, frequency); // TODO do this async
	}

	private void stopTask() {
		task.cancel();
	}

	static class DtrTask implements Runnable {

		@Inject
		private Factions factions;

		@Inject
		private TellService tell;

		private Instant now;
		private BigDecimal baseRate;
		private BigDecimal perPlayerRate;

		@Override
		public void run() {
			now = TemporalHelper.instantNow();
			baseRate = factions.settings().dtrRegenBaseAmount();
			perPlayerRate = factions.settings().dtrRegenPerPlayerAmount();
			factions.getAllFactions().forEach(this::regenDtr);
		}

		private void regenDtr(Faction faction) {
			DTR deathsTillRaidable = faction.getDeathsTillRaidable();
			if (deathsTillRaidable == null) {
				return;
			}

			List<Player> online = EntityHelper.getOnlineDenizens(faction).collect(Collectors.toList());
			Instant freeze = deathsTillRaidable.getFreezeExpiration();
			if (freeze != null) {
				if (freeze.isAfter(now)) {
					// TODO tell faction their freeze is over
					deathsTillRaidable.setFreezeExpiration(null);

					for (Player denizen : online) {
						tell.send(denizen, "factions-dtr-freeze-expired");
					}
				} else {
					return;
				}
			}

			BigDecimal rate = baseRate;
			if (!online.isEmpty()) {
				rate = rate.add(perPlayerRate.multiply(BigDecimal.valueOf(online.size())));
			}

			BigDecimal negative = deathsTillRaidable.getNegativePoints();
			negative = negative.subtract(rate).setScale(2, RoundingMode.HALF_UP);
			if (negative.compareTo(BigDecimal.ZERO) > 0) {
				deathsTillRaidable.setNegativePoints(negative);
				faction.setDeathsTillRaidable(deathsTillRaidable);
			} else {
				faction.setDeathsTillRaidable(null);
				for (Player denizen : online) {
					tell.send(denizen, "factions-dtr-regen-done");
				}
			}

			factions.persistFaction(faction);
		}
	}

}
