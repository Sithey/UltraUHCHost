package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Barebones extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Barebones";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BAREBONES.getDescription().split(" ")){
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
        return new ItemCreator(Material.BONE).setName(getName() + " : " + Scenario.BAREBONES.isEnabled()).setAmount(Scenario.BAREBONES.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND, 2));
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.ARROW, 32));
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.STRING, 2));
        player.getWorld().dropItem(player.getLocation(), new ItemCreator(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").getItem());
    }

    @Override
    public String getPath() {
        return "barebones";
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void Break(BlockBreakEvent ev) {
        Block b = ev.getBlock();
        org.bukkit.Location loc = b.getLocation();
            if (b.getType() == Material.DIAMOND_ORE) {
                if (Scenario.CUTCLEAN.isEnabled()) {
                    b.getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
                    ExperienceOrb exp = b.getWorld().spawn(b.getLocation(), ExperienceOrb.class);
                    exp.setExperience(3);
                }
                else b.getWorld().dropItem(loc, new ItemStack(Material.IRON_ORE));
                b.setType(Material.AIR);
            }
            if (b.getType() == Material.GOLD_ORE) {
                if (Scenario.CUTCLEAN.isEnabled()) {
                    b.getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
                    ExperienceOrb exp = b.getWorld().spawn(b.getLocation(), ExperienceOrb.class);
                    exp.setExperience(3);
                }
                else b.getWorld().dropItem(loc, new ItemStack(Material.IRON_ORE));
                b.setType(Material.AIR);
            }
            if (b.getType() == Material.LAPIS_ORE) {
                if (Scenario.CUTCLEAN.isEnabled()) {
                    b.getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
                    ExperienceOrb exp = b.getWorld().spawn(b.getLocation(), ExperienceOrb.class);
                    exp.setExperience(3);
                }
                else b.getWorld().dropItem(loc, new ItemStack(Material.IRON_ORE));
                b.setType(Material.AIR);
            }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onCraft(PrepareItemCraftEvent e) {
        if (((e.getInventory() instanceof CraftingInventory))) {
            CraftingInventory inv = e.getInventory();
            ItemStack AIR = new ItemStack(Material.AIR);
            if (inv.getResult().getType() == Material.ENCHANTMENT_TABLE || inv.getResult().getType() == Material.ANVIL)
                inv.setResult(AIR);
        }
    }
}
