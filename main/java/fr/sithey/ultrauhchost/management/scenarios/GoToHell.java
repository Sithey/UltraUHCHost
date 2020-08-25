package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.player.Rule;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoToHell extends CustomScenario {
    @Override
    public String getName() {
        return "Go To Hell";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.GOTOHELL.getDescription().split(" ")){
            newmsg.append(bout).append(" ");
            if(i == 8){
                textes.add(newmsg.toString());
                newmsg = new StringBuilder(ChatColor.GRAY + "");
                i = 0;
            }
            i++;
        }
        if(!newmsg.toString().equals(ChatColor.GRAY + "")){
            textes.add(newmsg.toString());
        }
        textes.add("");
        textes.add("§8§m--------------------------------------");
        return new ItemCreator(Material.OBSIDIAN).setName(getName() + " : " + Scenario.GOTOHELL.isEnabled()).setAmount(Scenario.GOTOHELL.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        RuleBoolean.NETHER.setEnabled(true);
    }

    @Override
    public void scatterPlayer(Player player) {

    }


    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {
        Bukkit.broadcastMessage(Message.PREFIX.getMessage() + Scenario.GOTOHELL.getDescription());
        new BukkitRunnable() {
            @Override
            public void run() {
                for (UPlayer uPlayer : UPlayer.getAlivePlayers()){
                    if (!uPlayer.getPlayer().getWorld().getEnvironment().equals(World.Environment.NETHER)){
                        uPlayer.getPlayer().damage(1);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20 * 30);
    }

    @Override
    public void onDeath(Player player, Player killer) {

    }

    @Override
    public String getPath() {
        return "gotohell";
    }
}
