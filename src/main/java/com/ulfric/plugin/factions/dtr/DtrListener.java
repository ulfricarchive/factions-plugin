package com.ulfric.plugin.factions.dtr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.commons.time.TemporalHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.factions.model.dtr.DTR;
import com.ulfric.plugin.factions.model.dtr.RaidHelper;
import com.ulfric.plugin.locale.TellService;

public class DtrListener implements Listener {

	static class H {
		Duration d;
	}

	@Inject
	private Factions factions;

	@Inject
	private TellService tell;

	@EventHandler
	public void on(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Faction faction = EntityHelper.getFactionOf(player);
		if (faction != null) {
			BigDecimal maxPower = RaidHelper.calculateMaxPower(faction);
			BigDecimal deathCost = deathCost(faction);

			BigDecimal negative = null;
			DTR deathsTillRaidable = faction.getDeathsTillRaidable();
			if (deathsTillRaidable != null) {
				negative = deathsTillRaidable.getNegativePoints();
			} else {
				deathsTillRaidable = new DTR();
			}

			if (negative == null) {
				negative = BigDecimal.ZERO;
			} else {
				negative = negative.plus();
			}
			negative = negative.add(deathCost);
			if (negative.compareTo(maxPower) > 0) {
				negative = maxPower;
			}
			negative = negative.negate();

			Duration duration = Duration.parse(factions.settings().dtrRegenFreeze());
			deathsTillRaidable.setFreezeExpiration(TemporalHelper.instantNowPlus(duration));

			faction.setDeathsTillRaidable(deathsTillRaidable);
			factions.persistFaction(faction);

			notifyFaction(player, faction);
		}
	}

	private BigDecimal deathCost(Faction faction) {
		int members = MapHelper.size(faction.getMembersToRoles());
		BigDecimal deathCost = RaidHelper.MEMBER_POWER_WORTH;
		if (members == 1) {
			deathCost = deathCost.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP)
					.setScale(2, RoundingMode.HALF_UP);
		}
		return deathCost;
	}

	private void notifyFaction(Player player, Faction faction) {
		Details details = Details.of(
				Detail.of("dead", player),
				Detail.of("faction", faction));
		UUID uniqueId = player.getUniqueId();
		EntityHelper.getOnlineDenizens(faction)
			.filter(online -> !online.getUniqueId().equals(uniqueId))
			.forEach(online -> tell.send(online, "factions-dtr-death-notice-by", details));

		tell.send(player, "factions-dtr-death-notice", details);
	}

}
