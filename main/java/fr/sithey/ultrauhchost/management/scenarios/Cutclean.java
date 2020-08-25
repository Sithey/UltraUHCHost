package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.scenarios.onepertwo.OnePerTwoObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Cutclean extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Cutclean";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.CUTCLEAN.getDescription().split(" ")){
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
        return new ItemCreator(Material.IRON_INGOT).setName(getName() + " : " + Scenario.CUTCLEAN.isEnabled()).setAmount(Scenario.CUTCLEAN.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "cutclean";
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
        if (entity.getType() == EntityType.CHICKEN) {
            for (ItemStack drops : e.getDrops()) {
                if (drops.getType() == Material.RAW_CHICKEN) {
                    drops.setType(Material.COOKED_CHICKEN);
                }
            }
        }
        if (entity.getType() == EntityType.SHEEP) {
            for (ItemStack drops : e.getDrops()) {
                if (drops.getType() == Material.MUTTON) {
                    drops.setType(Material.COOKED_MUTTON);
                }
            }
        }
        if (entity.getType() == EntityType.RABBIT) {
            for (ItemStack drops : e.getDrops()) {
                if (drops.getType() == Material.RABBIT) {
                    drops.setType(Material.COOKED_RABBIT);
                }
            }
        }
        if ((entity.getType() == EntityType.COW) || (entity.getType() == EntityType.MUSHROOM_COW)) {
            for (ItemStack drops : e.getDrops()) {
                if (drops.getType() == Material.RAW_BEEF) {
                    drops.setType(Material.COOKED_BEEF);
                }
            }
        }
        if (entity.getType() == EntityType.PIG) {
            for (ItemStack drops : e.getDrops()) {
                if (drops.getType() == Material.PORK) {
                    drops.setType(Material.GRILLED_PORK);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onMine(BlockBreakEvent event){
        if (Scenario.UNSURDEUXORES.isEnabled() || Scenario.BAREBONES.isEnabled() || Scenario.DOUBLEORNOTHING.isEnabled()){
            return;
        }
        Location loc = new Location(event.getBlock().getWorld(), event.getBlock().getLocation().getBlockX() + 0.5D, event.getBlock().getLocation().getBlockY() + 0.5D, event.getBlock().getLocation().getBlockZ() + 0.5D);

        if (!Scenario.IRONDROP.isEnabled() && event.getBlock().getType().equals(Material.IRON_ORE)){
            event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
            ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
            exp.setExperience(2);
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
        }
        if (event.getBlock().getType().equals(Material.GOLD_ORE)){
            event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT));
            ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
            exp.setExperience(5);
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
        }
    }


}
