package fr.sithey.ultrauhchost.management.scenarios.onepertwo;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class OnePerTwo extends CustomScenario implements Listener {
    private Material[] material = new Material[6];

    @Override
    public String getName() {
        return "1/2 Ores";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.UNSURDEUXORES.getDescription().split(" ")){
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
        return new ItemCreator(Material.COAL_ORE).setName(getName() + " : " + Scenario.UNSURDEUXORES.isEnabled()).setAmount(Scenario.UNSURDEUXORES.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        material[0] = Material.COAL_ORE;
        material[1] = Material.IRON_ORE;
        material[2] = Material.GOLD_ORE;
        material[3] = Material.LAPIS_ORE;
        material[4] = Material.DIAMOND_ORE;
        material[5] = Material.REDSTONE_ORE;
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
        return "1/2ores";
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onMine(BlockBreakEvent event){
        UPlayer u = UPlayer.getUPlayer(event.getPlayer().getUniqueId());
        for (Material material : material){
            if (material.equals(event.getBlock().getType())){
                OnePerTwoObject o;
                if (OnePerTwoObject.getObject(u) == null)
                    o = new OnePerTwoObject(u);
                else o = OnePerTwoObject.getObject(u);
                if (o != null) {
                    if (o.getMat().contains(material)) {
                        o.getMat().remove(material);
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                    }else{
                        Location loc = new Location(event.getBlock().getWorld(), event.getBlock().getLocation().getBlockX() + 0.5D, event.getBlock().getLocation().getBlockY() + 0.5D, event.getBlock().getLocation().getBlockZ() + 0.5D);
                        o.getMat().add(material);
                        if (Scenario.SLUTCLEAN.isEnabled()) {
                            Player player = event.getPlayer();
                            if (player.getPlayer().getInventory().getArmorContents()[0].getType() == Material.AIR && player.getPlayer().getInventory().getArmorContents()[1].getType() == Material.AIR && player.getPlayer().getInventory().getArmorContents()[2].getType() == Material.AIR && player.getPlayer().getInventory().getArmorContents()[3].getType() == Material.AIR) {
                                if (event.getBlock().getType().equals(Material.IRON_ORE)) {
                                    event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
                                    ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
                                    exp.setExperience(2);
                                    event.setCancelled(true);
                                    event.getBlock().setType(Material.AIR);
                                }
                                if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
                                    event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT));
                                    ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
                                    exp.setExperience(5);
                                    event.setCancelled(true);
                                    event.getBlock().setType(Material.AIR);
                                }
                            }
                        }else
                        if (Scenario.CUTCLEAN.isEnabled()) {
                            if (event.getBlock().getType().equals(Material.IRON_ORE)) {
                                event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT));
                                ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
                                exp.setExperience(2);
                                event.setCancelled(true);
                                event.getBlock().setType(Material.AIR);
                            }
                            if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
                                event.getBlock().getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT));
                                ExperienceOrb exp = loc.getWorld().spawn(loc, ExperienceOrb.class);
                                exp.setExperience(5);
                                event.setCancelled(true);
                                event.getBlock().setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }
    }

}
