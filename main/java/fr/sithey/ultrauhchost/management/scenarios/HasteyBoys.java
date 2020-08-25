package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HasteyBoys extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Hastey Boys";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.HASTEYBOYS.getDescription().split(" ")){
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
        return new ItemCreator(Material.GOLD_PICKAXE).setName(getName() + " : " + Scenario.HASTEYBOYS.isEnabled()).setAmount(Scenario.HASTEYBOYS.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "hasteyboys";
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        if (isUpgradeItem(e.getCurrentItem().getType()))
            e.setCurrentItem(enchantAndUpgrade(e.getCurrentItem()));
    }

    public Boolean isUpgradeItem(Material type) {
        return type == Material.WOOD_AXE || type == Material.WOOD_PICKAXE || type == Material.WOOD_SPADE
                || type == Material.GOLD_AXE || type == Material.GOLD_PICKAXE || type == Material.GOLD_SPADE
                || type == Material.STONE_AXE || type == Material.STONE_PICKAXE || type == Material.STONE_SPADE
                || type == Material.IRON_AXE || type == Material.IRON_PICKAXE || type == Material.IRON_SPADE
                || type == Material.DIAMOND_AXE || type == Material.DIAMOND_PICKAXE || type == Material.DIAMOND_SPADE;
    }

    public ItemStack enchantAndUpgrade(ItemStack item) {

        item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 2);

        return item;
    }


}
