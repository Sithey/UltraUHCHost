package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IronDrop extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Iron Drop";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.IRONDROP.getDescription().split(" ")){
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
        return new ItemCreator(Material.IRON_INGOT).setName(getName() + " : " + Scenario.IRONDROP.isEnabled()).setAmount(Scenario.IRONDROP.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {

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
        return "irondrop";
    }

    @EventHandler
    public void onIronBlock(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.IRON_ORE)){
            e.setCancelled(true);
            e.getBlock().setType(Material.AIR);
        }
    }
    @EventHandler
    public void onIronEntity(EntityDeathEvent e) {
        if (e.getEntity().getType().equals(EntityType.ZOMBIE)){
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.IRON_INGOT, 1));
            if (new Random().nextInt(100) <= 10){
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.RED_ROSE, 1));
            }
        }
    }

}
