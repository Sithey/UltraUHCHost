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
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MobShots extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Mobs Shots";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.MOBSHOTS.getDescription().split(" ")){
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
        return new ItemCreator(Material.BOW).setName(getName() + " : " + Scenario.MOBSHOTS.isEnabled()).setAmount(Scenario.MOBSHOTS.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "mobshots";
    }

    @EventHandler
    public void onShot(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile && event.getDamager().getType().equals(EntityType.ARROW)){
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player){
                Player player = (Player) projectile.getShooter();
                if (player.getLocation().distance(event.getEntity().getLocation()) < 5)
                    return;
                if (player.getLocation().distance(event.getEntity().getLocation()) <= 20){
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SILVERFISH);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SILVERFISH);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SILVERFISH);
                }else if (player.getLocation().distance(event.getEntity().getLocation()) <= 40){
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.ZOMBIE);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.ZOMBIE);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.ZOMBIE);
                }else if (player.getLocation().distance(event.getEntity().getLocation()) <= 60){
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                }else if (player.getLocation().distance(event.getEntity().getLocation()) <= 85){
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.WITCH);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.WITCH);
                }else {
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                    player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                }

            }
        }
    }
}
