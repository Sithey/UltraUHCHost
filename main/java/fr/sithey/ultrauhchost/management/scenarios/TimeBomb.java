package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.InventoryUtils;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TimeBomb extends CustomScenario{

    @Override
    public String getName() {
        return "TimeBomb";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.TIMEBOMB.getDescription().split(" ")){
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
        return new ItemCreator(Material.TNT).setName(getName() + " : " + Scenario.TIMEBOMB.isEnabled()).setAmount(Scenario.TIMEBOMB.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

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
        Location loc = player.getLocation().clone();
        Block block = player.getLocation().getBlock();

        block = block.getRelative(BlockFace.DOWN);
        block.setType(Material.CHEST);

        Chest chest = (Chest) block.getState();

        block = block.getRelative(BlockFace.NORTH);
        block.setType(Material.CHEST);

        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() == Material.AIR) {
                continue;
            }

            chest.getInventory().addItem(item);
        }
        InventoryUtils.addToChest(chest.getInventory());
        if (RuleBoolean.GHEAD.isEnabled())
            chest.getInventory().addItem(new ItemCreator(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").getItem());
        if (RuleBoolean.GAPPLE.isEnabled())
            chest.getInventory().addItem(new ItemCreator(Material.GOLDEN_APPLE).getItem());

        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item == null || item.getType() == Material.AIR) {
                continue;
            }

            chest.getInventory().addItem(item);
        }

        final ArmorStand stand = player.getWorld().spawn(chest.getLocation().clone().add(0.5, 1, 0), ArmorStand.class);

        stand.setCustomNameVisible(true);
        stand.setSmall(true);

        stand.setGravity(false);
        stand.setVisible(false);

        stand.setMarker(true);

        new BukkitRunnable() {
            private int time = 30 + 1; // add one for countdown.

            public void run() {
                time--;

                if (time == 0) {
                    Bukkit.broadcastMessage(Message.PREFIX.getMessage() +  "§a" + player.getName() + "'s §fcorpse has exploded!");

                    loc.getBlock().setType(Material.AIR);

                    loc.getWorld().createExplosion(loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5, 2, false, true);
                    loc.getWorld().strikeLightning(loc); // Using actual lightning to kill the items.

                    stand.remove();
                    cancel();
                    return;
                }
                else if (time == 1) {
                    stand.setCustomName("§4" + time + "s");
                }
                else if (time == 2) {
                    stand.setCustomName("§c" + time + "s");
                }
                else if (time == 3) {
                    stand.setCustomName("§6" + time + "s");
                }
                else if (time <= 15) {
                    stand.setCustomName("§e" + time + "s");
                }
                else {
                    stand.setCustomName("§a" + time + "s");
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }

    @Override
    public String getPath() {
        return "timebomb";
    }


}
