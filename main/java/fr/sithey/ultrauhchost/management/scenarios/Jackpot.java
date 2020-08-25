package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jackpot extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Jackpot";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.JACKPOT.getDescription().split(" ")){
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
        return new ItemCreator(Material.GOLD_INGOT).setName(getName() + " : " + Scenario.JACKPOT.isEnabled()).setAmount(Scenario.JACKPOT.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        Bukkit.broadcastMessage(Message.PREFIX.getMessage() + player.getName() + " drop the jackpot of " + diamond + " diamonds and " + gold + " golds.");
        if (killer == null) {
            Bukkit.broadcastMessage(Message.PREFIX.getMessage() + player.getName() + " died cause was pve, Cordinate : x:" + player.getLocation().getBlock().getX() + " y: " + player.getLocation().getBlock().getY()+ " z: " + player.getLocation().getBlock().getZ());
        }
            if (diamond != 0) {
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND, diamond));
                diamond = 0;
            }
            if (gold != 0) {
                player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLD_INGOT, diamond));
                gold = 0;
            }

    }

    @Override
    public String getPath() {
        return "jackpot";
    }
    private int diamond = 0;
    private int gold = 0;

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.DIAMOND_ORE)){
            diamond++;
        }
        if (e.getBlock().getType().equals(Material.GOLD_ORE)){
            gold++;
        }
    }

    @EventHandler
    public void onBreak(PlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("Jackpot")){
            e.setCancelled(true);
            e.getPlayer().sendMessage(Message.PREFIX.getMessage() + "jackpot of " + diamond + " diamonds and " + gold + " golds.");
        }
    }

}
