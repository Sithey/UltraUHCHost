package fr.sithey.ultrauhchost.command;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpopCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            if (cmd.getName().equalsIgnoreCase("helpop")){
                Player player = (Player) sender;
                if (args.length > 0) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 0; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    if (!Main.getInstance().getUHC().getStatut().isLobby()) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("ultrauhc.helpop") && !UPlayer.getUPlayer(all.getUniqueId()).isPlaying()) {
                                all.sendMessage(Message.HELPOPRECEIVE.getMessage().replace("<player>", player.getName()).replace("<text>", message));
                            }
                        }
                    }else{
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("ultrauhc.helpop")) {
                                all.sendMessage(Message.HELPOPRECEIVE.getMessage().replace("<player>", player.getName()).replace("<text>", message));
                            }
                        }
                    }
                    player.sendMessage(Message.HELPOPSENT.getMessage());
                }
            }
        }
        return false;
    }
}
