package com.ulfric.plugin.factions.denizens.membership;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.entities.components.Component;
import com.ulfric.plugin.entities.components.ComponentKey;
import com.ulfric.plugin.entities.components.name.NameComponent;
import com.ulfric.plugin.factions.Factions;

public class MembershipComponent extends Component {

	public static final ComponentKey<MembershipComponent> KEY = MembershipComponentKey.INSTANCE;

	private String faction;

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public void setFactionByEntity(Entity entity) {
		if (entity == null) {
			setFaction(null);
			return;
		}

		String name = extractFormalName(entity)
				.orElseGet(() -> entity.getLocation().getKey().toString());

		setFaction(name);
	}

	private Optional<String> extractFormalName(Entity entity) {
		NameComponent formalName = entity.getComponent(NameComponent.KEY);
		if (formalName != null) {
			return Optional.ofNullable(formalName.getName());
		}
		return Optional.empty();
	}

	public CompletableFuture<Entity> getFactionEntity() {
		String faction = getFaction();

		if (StringUtils.isEmpty(faction)) {
			return null;
		}

		return Factions.getFaction(faction);
	}

}
