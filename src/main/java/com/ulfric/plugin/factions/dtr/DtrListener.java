package com.ulfric.plugin.factions.dtr;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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

	@Inject
	private Factions factions;

	@Inject
	private TellService tell;

	@Inject
	private RaidHelper raidHelper;

	@EventHandler
	public void on(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Faction faction = EntityHelper.getFactionOf(player);
		if (faction != null) {
			BigDecimal negative;
			DTR deathsTillRaidable = faction.getDeathsTillRaidable();
			if (deathsTillRaidable != null) {
				negative = deathsTillRaidable.getNegativePoints();
				if (negative == null) {
					negative = BigDecimal.ZERO;
				}
			} else {
				negative = BigDecimal.ZERO;
			}

			negative = negative.add(BigDecimal.ONE);
			negative = negative.min(raidHelper.getDtrCap(faction));
			deathsTillRaidable.setNegativePoints(negative);

			Duration duration = Duration.parse(factions.settings().dtrRegenFreeze());
			deathsTillRaidable.setFreezeExpiration(TemporalHelper.instantNowPlus(duration));

			faction.setDeathsTillRaidable(deathsTillRaidable);
			factions.persistFaction(faction);

			notifyFaction(player, faction);
		}
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
