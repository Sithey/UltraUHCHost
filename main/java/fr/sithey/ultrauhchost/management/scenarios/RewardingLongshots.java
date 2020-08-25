package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RewardingLongshots extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Rewarding Longshots";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.REWARDINGLONGSHOTS.getDescription().split(" ")){
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
        return new ItemCreator(Material.ARROW).setName(getName() + " : " + Scenario.REWARDINGLONGSHOTS.isEnabled()).setAmount(Scenario.REWARDINGLONGSHOTS.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "rewardinglongshots";
    }

    @EventHandler
    public void onShot(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile && event.getDamager().getType().equals(EntityType.ARROW)){
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player){
                Player player = (Player) projectile.getShooter();
                if (player.getLocation().distance(event.getEntity().getLocation()) < 30)
                    return;
                if (player.getLocation().distance(event.getEntity().getLocation()) <= 50){
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                }else if (player.getLocation().distance(event.getEntity().getLocation()) <= 100){
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
                }else if (player.getLocation().distance(event.getEntity().getLocation()) <= 200){
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 1));
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND, 1));
                }else {
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLD_INGOT, 3));
                    player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.DIAMOND, 5));
                }

            }
        }
    }
}
