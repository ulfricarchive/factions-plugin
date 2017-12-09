package com.ulfric.plugin.factions.settings;

import java.math.BigDecimal;

import com.ulfric.conf4j.ConfigurationBean;

public interface FactionsSettings extends ConfigurationBean {

	int maxPlayersPerFaction();

	String dtrRegenFrequency();

	String dtrRegenFreeze();

	BigDecimal dtrRegenBaseAmount();

	BigDecimal dtrRegenPerPlayerAmount();

}
