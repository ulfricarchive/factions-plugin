package com.ulfric.plugin.factions.model.dtr;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.plugin.factions.model.Faction;

public class RaidHelper {

	public static final BigDecimal MEMBER_POWER_WORTH = new BigDecimal("1.01"); // TODO configurable

	public static boolean isRaidable(Faction faction) {
		DTR deathsTillRaidable = faction.getDeathsTillRaidable();

		if (deathsTillRaidable == null) {
			return false;
		}

		return calculateDtr(deathsTillRaidable, faction.getMembersToRoles().size()).compareTo(BigDecimal.ZERO) <= 0;
	}

	public static BigDecimal calculateDtr(Faction faction) {
		int members = faction.getMembersToRoles().size();
		DTR deathsTillRaidable = faction.getDeathsTillRaidable();

		if (deathsTillRaidable == null) {
			return calculateDtr(BigDecimal.ZERO, members);
		}

		return calculateDtr(deathsTillRaidable, members);
	}

	private static BigDecimal calculateDtr(DTR deathsTillRaidable, int members) {
		BigDecimal negative = deathsTillRaidable.getNegativePoints();
		if (negative == null) {
			negative = BigDecimal.ZERO;
		}

		return calculateDtr(negative, members);
	}

	private static BigDecimal calculateDtr(BigDecimal negative, int members) {
		return MEMBER_POWER_WORTH.multiply(BigDecimal.valueOf(members))
			.subtract(negative)
			.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal calculateMaxPower(Faction faction) {
		int members = MapHelper.size(faction.getMembersToRoles());
		return MEMBER_POWER_WORTH.multiply(BigDecimal.valueOf(members));
	}

	private RaidHelper() {
	}

}
