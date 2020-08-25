package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.List;

public class SnowballFlight extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Snowball Flight";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for (String bout : Scenario.SNOWBALLFLIGHT.getDescription().split(" ")) {
            newmsg.append(bout).append(" ");
            if (i == 8) {
                textes.add(newmsg.toString());
                newmsg = new StringBuilder(ChatColor.GRAY + "");
                i = 0;
            }
            i++;
        }
        if (!newmsg.toString().equals(ChatColor.GRAY + "")) {
            textes.add(newmsg.toString());
        }
        textes.add("");
        textes.add("§8§m--------------------------------------");
        return new ItemCreator(Material.SNOW_BALL).setName(getName() + " : " + Scenario.SNOWBALLFLIGHT.isEnabled()).setAmount(Scenario.SNOWBALLFLIGHT.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "snowballflight";
    }


    @EventHandler
    public void projectileShot(final ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            final Player player = (Player)event.getEntity().getShooter();
            if (event.getEntity() instanceof Snowball) {
                player.spigot().setCollidesWithEntities(false);
                event.getEntity().setPassenger(player);
                player.setAllowFlight(true);
            }
        }
    }

    @EventHandler
    public void arrowHitPlayer(final ProjectileHitEvent event) {
        if (event.getEntity().getPassenger() instanceof Player) {
            final Player p = (Player)event.getEntity().getPassenger();
            p.spigot().setCollidesWithEntities(true);
            p.setAllowFlight(false);
            this.startScheduler(p);
        }
    }

    /*@EventHandler
    public void noDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
        }
    }*/

    @EventHandler
    public void cancelFlight(final EntityDismountEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                this.startScheduler(p);
            }
        }
    }

    public void startScheduler(final Player p) {
        new BukkitRunnable() {
            public void run() {
                if (p.isOnGround()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 5L);
    }

}
