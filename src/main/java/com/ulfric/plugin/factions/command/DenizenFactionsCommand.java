package com.ulfric.plugin.factions.command;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.bukkit.entity.Player;

import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.Lock;
import com.ulfric.plugin.entities.Entity;
import com.ulfric.plugin.factions.Factions;
import com.ulfric.plugin.locale.TellService;

public abstract class DenizenFactionsCommand extends FactionsCommand {

	@Override
	public void run(Context context) {
		runAsDenizen(context);
	}

	public final void runAsDenizen(Context context) {
		Player player = Context.getPlayer(context);

		Lock lock = context.getLock();
		context.getLock().setState(true);
		Factions.getDenizen(player.getUniqueId()).whenComplete((denizen, error) -> {
			if (error != null || denizen == null) {
				TellService.sendMessage(player, "factions-error-denizen-not-found");
				return;
			}

			Future<?> run = runAsDenizen(context, denizen);

			if (run == null) {
				return;
			}

			try {
				run.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e); // TODO error handling
			}
		}).thenRun(() -> lock.setState(false));
	}

	public Future<?> runAsDenizen(Context context, Entity denizen) {
		return null;
	}

}
