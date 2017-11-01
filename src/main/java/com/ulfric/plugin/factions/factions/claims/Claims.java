package com.ulfric.plugin.factions.factions.claims;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.ulfric.commons.bukkit.server.ShutdownOnFailure;
import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.RegionSpace;
import com.ulfric.commons.spatial.SpatialHash;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.extension.postconstruct.PostConstruct;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.factions.FactionSystem;
import com.ulfric.plugin.regions.RegionService;
import com.ulfric.plugin.services.Service;

public class Claims implements RegionService<Claims> {

	public static Claims get() {
		return Service.get(Claims.class); // TODO optimize by caching instances?
	}

	private final Map<UUID, ClaimSpace> claimsByWorld = new HashMap<>();

	@Inject
	private FactionSystem factions;

	@PostConstruct
	private void initialize() {
		loadClaimsFromDatabase();
	}

	@ShutdownOnFailure
	public void loadClaimsFromDatabase() {
		factions.getAllEntities().thenAccept(entities -> {
			entities.stream()
				.filter(Objects::nonNull)
				.forEach(instance -> {
					Entity entity = instance.get();
					if (entity == null) {
						return;
					}

					ClaimsComponent claimsComponent = entity.getComponent(ClaimsComponent.KEY);
					if (claimsComponent == null) {
						return;
					}

					List<Claim> claims = claimsComponent.getClaims();
					for (Claim claim : claims) {
						if (claim == null) {
							continue;
						}

						Region region = Claim.createRegion(instance, claim);
						getRegions(claim.getWorld()) // TODO handle null world by defaulting to bukkit world zero
							.add(region);
					}
				});
		}).join();
	}

	@Override
	public Class<Claims> getService() {
		return Claims.class;
	}

	@Override
	public RegionSpace getRegions(UUID world) {
		return claimsByWorld.computeIfAbsent(world, ignore -> new ClaimSpace());
	}

	private final class ClaimSpace implements RegionSpace {
		private final SpatialHash<Region> claims = new SpatialHash<>(100);

		@Override
		public List<Region> getRegions(int x, int z) {
			return claims.get(x, z);
		}

		@Override
		public void add(Region region) {
			claims.put(region.getBounds(), region);
		}

		@Override
		public void remove(Region region) {
			claims.remove(region::equals); // TODO is this what we want?
		}
	}

}
