package com.ulfric.plugin.factions.factions.claims;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;

public class ClaimsComponent extends Component {

	public static final ComponentKey<ClaimsComponent> KEY = ClaimsComponentKey.INSTANCE;

	private List<Claim> claims;

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public void addClaim(Claim claim) {
		Objects.requireNonNull(claim, "claim");

		List<Claim> claims = getClaims();
		if (claims == null) {
			setClaims(new ArrayList<>());
			claims = getClaims();
		}

		claims.add(claim);
	}

}
