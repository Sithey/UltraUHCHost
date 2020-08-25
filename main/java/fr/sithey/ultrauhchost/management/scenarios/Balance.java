package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.scenarios.onepertwo.OnePerTwoObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balance extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Balance";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BALANCE.getDescription().split(" ")){
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
        return new ItemCreator(Material.DIAMOND).setName(getName() + " : " + Scenario.BALANCE.isEnabled()).setAmount(Scenario.BALANCE.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "balance";
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onMine(BlockBreakEvent event){
        UPlayer u = UPlayer.getUPlayer(event.getPlayer().getUniqueId());
            if (event.getBlock().getType().equals(Material.DIAMOND_ORE)){
                if (u.getDiamonds() >= 30){
                    int random = new Random().nextInt(7);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }else if (u.getDiamonds() >= 25){
                    int random = new Random().nextInt(6);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }else if (u.getDiamonds() >= 20){
                    int random = new Random().nextInt(5);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }else if (u.getDiamonds() >= 15){
                    int random = new Random().nextInt(4);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }else if (u.getDiamonds() >= 10){
                    int random = new Random().nextInt(3);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }else if (u.getDiamonds() >= 5){
                    int random = new Random().nextInt(2);
                    if (random >= 2){
                        event.setCancelled(true);
                        event.getBlock().setType(Material.AIR);
                        event.getPlayer().sendMessage(Message.PREFIX.getMessage() + "Uh oh! Diamonds might disappear when mined now! to player");
                    }
                }
            }
    }

}
