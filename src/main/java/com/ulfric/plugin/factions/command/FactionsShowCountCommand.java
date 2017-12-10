package com.ulfric.plugin.factions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.factions.model.EntityHelper;

@Name("count")
@Alias("popularity")
public class FactionsShowCountCommand extends FactionsShowCommand {

	@Override
	public void run() {
		if (target == null) {
			target = EntityHelper.getFactionOf(denizen);

			if (target == null) {
				tell("factions-show-count-target-required");
				return;
			}
		}

		tell("factions-show-count");
	}

}
