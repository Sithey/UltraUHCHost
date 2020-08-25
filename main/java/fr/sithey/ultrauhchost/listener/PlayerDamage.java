package fr.sithey.ultrauhchost.listener;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.Absorption;
import fr.sithey.ultrauhchost.lib.ScoreboardLife;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            UPlayer up = UPlayer.getUPlayer(player.getUniqueId());
            if (Time.DAMAGE.getTime() >= 0 || !up.isPlaying() || UPlayer.nodamage.contains(up)) {
                event.setCancelled(true);
                return;
            }
            ScoreboardLife.updateHealth(player);
            double value = (player.getHealth() + new Absorption(player).getAbso()) - event.getFinalDamage();
            if (value <= 0) {
                event.setCancelled(true);
                if (event instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                    if (e.getDamager() instanceof Player) {
                        UPlayer uk = UPlayer.getUPlayer(e.getDamager().getUniqueId());
                        up.setDeath(uk);
                    }else  if (e.getDamager() instanceof Arrow){
                        Arrow arrow = (Arrow)e.getDamager();
                        if (arrow.getShooter() instanceof Player) {
                            UPlayer uk = UPlayer.getUPlayer(((Player) arrow.getShooter()).getUniqueId());
                            up.setDeath(uk);
                        }else{
                            up.setDeath(null);
                        }
                    } else {
                        up.setDeath(null);
                    }

                } else {
                    up.setDeath(null);
                }
            }else{
                if (event instanceof EntityDamageByEntityEvent) {
                    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
                    if (e.getDamager() instanceof Arrow) {
                        final Arrow arrow = (Arrow)e.getDamager();
                        if (arrow.getShooter() instanceof Player) {
                            final Player s = (Player)arrow.getShooter();
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    int percent = (int) (player.getHealth() * 5) + (int) (new Absorption(player).getAbso() * 5);
                                    if (percent > 0) {
                                        s.sendMessage(Message.PREFIX.getMessage() + ChatColor.GREEN + player.getName() + ChatColor.AQUA + " : " + ChatColor.RED + percent + ChatColor.AQUA + " %");
                                    }
                                }
                            }.runTaskLaterAsynchronously(Main.getInstance(), 1);

                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityGainHeal(EntityRegainHealthEvent event){
        if (event.getEntity() instanceof Player) {
            ScoreboardLife.updateHealth(((Player) event.getEntity()).getPlayer());
        }
    }
}
