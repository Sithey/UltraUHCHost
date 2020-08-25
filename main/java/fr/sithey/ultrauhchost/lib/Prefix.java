package fr.sithey.ultrauhchost.lib;

import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.ChatColor;

import java.util.Random;

public class Prefix {

	private static final ChatColor[] colors = { ChatColor.RED, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.DARK_PURPLE,
			ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE, ChatColor.DARK_GREEN, ChatColor.GRAY };
	private static final String[] symbols = {  "§m",  "§n",
			 "§o",  "§l",  "§o§m",
			 "§o§n", "§o§l", "§l§m",  "§l§n"};

	public final ChatColor color;
	public final String symbol;

	public Prefix(ChatColor color, String symbol) {
		this.color = color;
		this.symbol = symbol;
	}

	public static Prefix getRandom() {
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			ChatColor color = colors[random.nextInt(colors.length)];
			String symbol = symbols[random.nextInt(symbols.length)];

			boolean exists = false;
			for (UTeam team : UTeam.teams) {
				if ((team.getName().color.equals(color)) && (team.getName().symbol.equals(symbol))) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				return new Prefix(color, symbol);
			}
		}
		return new Prefix(ChatColor.GRAY, "");
	}

	public static Prefix getTaupePrefix(int i){
		return new Prefix(ChatColor.DARK_RED, "Taupe " + ChatColor.GOLD + i + " " + ChatColor.WHITE);
	}
}
