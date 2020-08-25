package fr.sithey.ultrauhchost.command;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.player.Rule;
import fr.sithey.ultrauhchost.gui.player.Scenarios;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RulesCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            if (cmd.getName().equalsIgnoreCase("rules")){
                Player player = (Player) sender;
                Main.getInstance().getGui().open(player, Rule.class);
            }
            if (cmd.getName().equalsIgnoreCase("scenarios")){
                Player player = (Player) sender;
                Main.getInstance().getGui().open(player, Scenarios.class);
            }
        }
        return false;
    }
}
