package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.factions.model.EntityHelper;
import com.ulfric.plugin.factions.model.Faction;

@Name("show")
@Alias({ "who", "info" })
public class FactionsShowCommand extends FactionsCommand {

	@Argument(optional = true)
	protected Faction target;

	@Override
	public void run() {
		if (target == null) {
			target = EntityHelper.getFactionOf(denizen);

			if (target == null) {
				tell("factions-show-target-required");
				return;
			}
		}

		tell("factions-show");
		recordShow();
	}

	private void recordShow() {
		Integer showCount = target.getShowCount();
		if (showCount == null) {
			showCount = 1;
		} else {
			showCount += 1;
		}
		target.setShowCount(showCount);
		factions.persistFaction(target);
	}

}
