package com.ulfric.plugin.factions.model.dtr;

import java.math.BigDecimal;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.extension.postconstruct.PostConstruct;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.factions.model.Faction;
import com.ulfric.plugin.services.Service;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class RaidHelper implements Service<RaidHelper> {

	private static final BigDecimal HUGE = BigDecimal.valueOf(Long.MAX_VALUE);

	private final Int2ObjectMap<BigDecimal> capCache = new Int2ObjectOpenHashMap<>();

	@Inject
	private Factions factions;

	@PostConstruct
	private void loadCapCache() {
		capCache.putAll(factions.settings().powerCaps());
	}

	@Override
	public Class<RaidHelper> getService() {
		return RaidHelper.class;
	}

	public boolean isRaidable(Faction faction) {
		DTR dtr = faction.getDeathsTillRaidable();
		if (dtr == null) {
			return false;
		}

		BigDecimal negative = dtr.getNegativePoints();
		if (negative == null) {
			return false;
		}

		return calculateDtr(faction, negative).compareTo(BigDecimal.ZERO) <= 0;
	}

	public BigDecimal calculateDtr(Faction faction) {
		BigDecimal negative;

		DTR dtr = faction.getDeathsTillRaidable();
		if (dtr == null) {
			negative = BigDecimal.ZERO;
		} else {
			negative = dtr.getNegativePoints();

			if (negative == null) {
				negative = BigDecimal.ZERO;
			}
		}

		return calculateDtr(faction, negative);
	}

	private BigDecimal calculateDtr(Faction faction, BigDecimal negative) {
		BigDecimal cap = getDtrCap(faction);

		return cap.subtract(negative);
	}

	public BigDecimal getDtrCap(Faction faction) {
		int members = MapHelper.size(faction.getMembersToRoles());
		BigDecimal cap = capCache.get(members);
		return cap == null ? HUGE : cap;
	}

}
