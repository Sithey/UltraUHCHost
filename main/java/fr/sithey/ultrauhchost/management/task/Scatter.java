package fr.sithey.ultrauhchost.management.task;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.ScoreboardLife;
import fr.sithey.ultrauhchost.lib.Title;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static fr.sithey.ultrauhchost.management.enumeration.Statut.GAME;

public class Scatter extends BukkitRunnable {
    private int statut = 1;
    public static List<UPlayer> scatter;
    private int border;

    public Scatter(int border, List<UPlayer> players) {
        this.border = border; scatter = new ArrayList<>();
        scatter.addAll(players);
    }

    @Override
    public void run() {
        if (statut == 1) {



            if (scatter.isEmpty()) {
                statut = 2;
                return;
            }

            UPlayer p = scatter.get(0);
            scatter.remove(0);
            if (p.getPlayer() == null) {
                return;
            }

            Location location = new Location(Bukkit.getWorld("world"), (new Random().nextInt(1) == 0 ? -1 : 1) * new Random().nextInt(border)
                    + 0.5, 100, (new Random().nextInt(1) == 0 ? -1 : 1) * new Random().nextInt(border) + 0.5);
            if (UTeam.getTeamWithPlayer(p.getPlayer()) != null){
                for (UPlayer t : UTeam.getTeamWithPlayer(p.getPlayer()).getAlive()){
                    scatter.remove(t);
                    t.getPlayer().getInventory().clear();
                    t.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 255));
                    t.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 200));
                    t.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 10));
                    t.getPlayer().teleport(location);
                    t.getPlayer().performCommand("rules");
                }
            }else{
                p.getPlayer().getInventory().clear();
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 255));
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 200));
                p.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 10));
                p.getPlayer().teleport(location);
                p.getPlayer().performCommand("rules");
            }
            return;
        }

        if (statut == 12 * 4){
            new Game().runTaskTimer(Main.getInstance(), 0, 20);
            ScoreboardLife.setup();
            for (UPlayer p : UPlayer.getAlivePlayers()){
                p.setStart();
            }
            for (Scenario scenario : Scenario.getEnabled()){
                scenario.getScenario().onStart();
            }

            cancel();
            return;
        }

        if (statut >= 2) {
            if (statut == 2 * 4) {
                Main.getInstance().getUHC().setStatut(GAME);
                Bukkit.getOnlinePlayers().forEach(p -> {
                    Title.sendTitle(p, 5, 10, 5, ChatColor.translateAlternateColorCodes('&', "&4&l10"), "");
                    p.playSound(p.getLocation(), Sound.CLICK, 2f, 2f);
                });
                Bukkit.broadcastMessage(Message.SCATTERFINISH.getMessage().replace("<online>", UPlayer.getAlivePlayers().size() + ""));
            }
            if (statut == 7 * 4)
                Bukkit.getOnlinePlayers().forEach(p -> {
                            Title.sendTitle(p, 5, 10, 5, ChatColor.translateAlternateColorCodes('&', "&c&l5"), "");
                            p.playSound(p.getLocation(), Sound.CLICK, 2f, 2f);
                        });

            if (statut == 9 * 4)
                Bukkit.getOnlinePlayers().forEach(p -> {
                    Title.sendTitle(p, 5, 10, 5, ChatColor.translateAlternateColorCodes('&', "&6&l3"), "");
                    p.playSound(p.getLocation(), Sound.CLICK, 2f, 2f);
                });
            if (statut == 10 * 4)
                Bukkit.getOnlinePlayers().forEach(p -> {
                    Title.sendTitle(p, 5, 10, 5, ChatColor.translateAlternateColorCodes('&', "&e&l2"), "");
                    p.playSound(p.getLocation(), Sound.CLICK, 2f, 2f);
                });
            if (statut == 11 * 4)
                Bukkit.getOnlinePlayers().forEach(p -> {
                    Title.sendTitle(p, 5, 10, 5, ChatColor.translateAlternateColorCodes('&', "&b&l1"), "");
                    p.playSound(p.getLocation(), Sound.CLICK, 2f, 2f);
                });
            statut++;
        }
    }
}
