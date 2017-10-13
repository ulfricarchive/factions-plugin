package com.ulfric.plugin.factions.factions.description;

import org.bukkit.ChatColor;

public enum DescriptionColor {

	WHITE(ChatColor.WHITE),
	YELLOW(ChatColor.YELLOW),
	GREEN(ChatColor.GREEN),
	BLUE(ChatColor.AQUA),
	PINK(ChatColor.LIGHT_PURPLE);

	private final ChatColor chatColor;

	DescriptionColor(ChatColor chatColor) {
		this.chatColor = chatColor;
	}

	public final ChatColor getChatColor() {
		return chatColor;
	}

}
