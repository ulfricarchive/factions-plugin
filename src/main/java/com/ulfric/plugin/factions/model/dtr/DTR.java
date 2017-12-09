package com.ulfric.plugin.factions.model.dtr;

import java.math.BigDecimal;
import java.time.Instant;

import com.ulfric.commons.value.Bean;

public class DTR extends Bean {

	private Instant freezeExpiration;
	private BigDecimal negativePoints;

	public Instant getFreezeExpiration() {
		return freezeExpiration;
	}

	public void setFreezeExpiration(Instant freezeExpiration) {
		this.freezeExpiration = freezeExpiration;
	}

	public BigDecimal getNegativePoints() {
		return negativePoints;
	}

	public void setNegativePoints(BigDecimal negativePoints) {
		this.negativePoints = negativePoints;
	}

}
