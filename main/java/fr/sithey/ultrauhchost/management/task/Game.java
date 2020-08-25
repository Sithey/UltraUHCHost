package fr.sithey.ultrauhchost.management.task;

import fr.sithey.ultrauhchost.lib.ScoreboardLife;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.sithey.ultrauhchost.management.enumeration.Time.*;

public class Game extends BukkitRunnable {
    @Override
    public void run() {
        for (Time time : Time.values()){
            if (time != Time.TIME){
                if (time.getTime() != 0)
                time.Broadcast();
                else {
                    if (time == DAMAGE){
                        Bukkit.broadcastMessage(Message.DAMAGENOW.getMessage());
                    }
                    if (time == FINALHEAL){
                        Bukkit.broadcastMessage(Message.FINALHEALNOW.getMessage());
                        for (Player player : Bukkit.getOnlinePlayers()){
                            player.setHealth(player.getMaxHealth());
                            ScoreboardLife.setHealth(player);
                        }
                    }

                    if (time == PVP){
                        Bukkit.broadcastMessage(Message.PVPNOW.getMessage());
                        for (World world : Bukkit.getWorlds()){
                            world.setPVP(true);
                        }
                        for (Scenario scenario : Scenario.getEnabled()){
                            scenario.getScenario().onPvP();
                        }
                    }

                    if (time == MEETUP){
                        Bukkit.broadcastMessage(Message.MEETUPNOW.getMessage().replace("<finalborder>", RuleInteger.FINALBORDERSIZE.getValue() + ""));
                        for (World world : Bukkit.getWorlds()){
                            world.getWorldBorder().setSize(RuleInteger.FINALBORDERSIZE.getValue(), 20 * 60);
                        }
                        for (Scenario scenario : Scenario.getEnabled()){
                            scenario.getScenario().onMeetup();
                        }
                    }
                }
            }
        }
        TIME.setTime(TIME.getTime() + 1);
    }
}
