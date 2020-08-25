package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ninjanaut extends CustomScenario{

    @Override
    public String getName() {
        return "Ninjanaut";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.NINJANAUT.getDescription().split(" ")){
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
        return new ItemCreator(Material.NETHER_STAR).setName(getName() + " : " + Scenario.NINJANAUT.isEnabled()).setAmount(Scenario.NINJANAUT.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    private UPlayer ninja = null;
    @Override
    public void onStart() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ninja = UPlayer.getAlivePlayers().get(new Random().nextInt(UPlayer.getAlivePlayers().size()));
                ninja.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                ninja.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1));
            }
        }.runTaskLater(Main.getInstance(), 10 * 20 * 60);
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
        if (killer != null){
            if (ninja.getPlayer().getName().equalsIgnoreCase(player.getName())){
                ninja = UPlayer.getUPlayer(killer.getUniqueId());
                ninja.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1));
                ninja.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 1));
            }
        }
    }

    @Override
    public String getPath() {
        return "ninjanaut";
    }

}
