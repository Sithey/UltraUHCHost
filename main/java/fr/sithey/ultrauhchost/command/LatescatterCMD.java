package fr.sithey.ultrauhchost.command;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class LatescatterCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("latescatter")) {
                if (!RuleBoolean.LATESCATTER.isEnabled())
                    return false;
                Player player = (Player) sender;

                UPlayer uPlayer = UPlayer.getUPlayer(player.getUniqueId());
                if (uPlayer.isCanlatescatter() && Time.PVP.getTime() > 0){
                    Bukkit.broadcastMessage(Message.LATESCATTERBROADCAST.getMessage().replace("<player>", player.getName()));
                    Location location = new Location(Bukkit.getWorld("world"), (new Random().nextInt(1) == 0 ? -1 : 1) * new Random().nextInt(((int) Bukkit.getWorld("world").getWorldBorder().getSize()) / 2)
                            + 0.5, 100, (new Random().nextInt(1) == 0 ? -1 : 1) * new Random().nextInt(((int) Bukkit.getWorld("world").getWorldBorder().getSize()) / 2) + 0.5);
                    uPlayer.getPlayer().teleport(location);
                    uPlayer.setStart();
                    UPlayer.nodamage.add(uPlayer);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            UPlayer.nodamage.remove(uPlayer);
                            if (uPlayer.getPlayer() != null)
                                uPlayer.getPlayer().sendMessage(Message.DAMAGENOW.getMessage());
                        }
                    }.runTaskLaterAsynchronously(Main.getInstance(), 15 * 20);
                }

            }
        }
        return false;
    }
}
