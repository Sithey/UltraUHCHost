package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
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
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SkyHigh extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Sky High";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.SKYHIGH.getDescription().split(" ")){
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
        return new ItemCreator(Material.FEATHER).setName(getName() + " : " + Scenario.SKYHIGH.isEnabled()).setAmount(Scenario.SKYHIGH.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {
        player.getInventory().addItem(new ItemCreator(Material.DIRT).setAmount(3).getItem());
    }


    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {
        Bukkit.broadcastMessage(Message.PREFIX.getMessage() + Scenario.SKYHIGH.getDescription());
        new BukkitRunnable() {
            @Override
            public void run() {
                for (UPlayer uPlayer : UPlayer.getAlivePlayers()){
                    if (uPlayer.getPlayer().getLocation().getY() <= 150){
                        uPlayer.getPlayer().damage(1);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20 * 30);
    }

    @Override
    public void onDeath(Player player, Player killer) {

    }

    @EventHandler
    public void place(BlockPlaceEvent ev) {
            Block b = ev.getBlock();
            Player p = ev.getPlayer();
            if (b.getType() == Material.DIRT)
                p.getInventory().setItemInHand(new ItemCreator(Material.DIRT).setAmount(3).getItem());
    }

    @Override
    public String getPath() {
        return "skyhigh";
    }
}
