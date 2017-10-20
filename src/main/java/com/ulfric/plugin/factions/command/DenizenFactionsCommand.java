package com.ulfric.plugin.factions.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.bukkit.entity.Player;

import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.locale.TellService;

public abstract class DenizenFactionsCommand extends FactionsCommand {

	protected Entity denizen;

	@Override
	public void run() {
		Player player = Context.getPlayer(context);

		Factions.getDenizen(player.getUniqueId()).whenComplete((denizen, error) -> {
			if (error != null || denizen == null) {
				TellService.sendMessage(player, "factions-error-denizen-not-found");
				return;
			}

			this.denizen = denizen;
			Future<?> run = runAsDenizen();

			if (run == null) {
				return;
			}

			try {
				run.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e); // TODO error handling
			}
		});
	}

	public Future<?> runAsDenizen() {
		return null;
	}

}
