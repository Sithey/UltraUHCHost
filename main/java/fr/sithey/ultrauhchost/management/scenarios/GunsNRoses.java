package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GunsNRoses extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Guns N Roses";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.GUNNROSES.getDescription().split(" ")){
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
        return new ItemCreator(Material.getMaterial(38)).setName(getName() + " : " + Scenario.GUNNROSES.isEnabled()).setAmount(Scenario.GUNNROSES.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if (event.getBlock().getType().equals(Material.getMaterial(38))) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            int i = new Random().nextInt(100);
            if (i <= 2) {
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.BOW));
            }else{
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.ARROW));
            }
        }
        if (event.getBlock().getType().equals(Material.getMaterial(175)) && event.getBlock().getData() == ((short) 4)) {
            int i = new Random().nextInt(100);
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            if (i <= 2) {
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.BOW));
            }else{
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.ARROW, 4));
            }
        }
    }

    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {

    }

    @Override
    public void onDeath(Player player, Player killer) {

    }

    @Override
    public String getPath() {
        return "gunnroses";
    }
}
