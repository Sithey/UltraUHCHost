package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotionSwap extends CustomScenario{

    @Override
    public String getName() {
        return "Potion Swap";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.POTIONSWAP.getDescription().split(" ")){
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
        return new ItemCreator(Material.POTION).setName(getName() + " : " + Scenario.POTIONSWAP.isEnabled()).setAmount(Scenario.POTIONSWAP.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void scatterPlayer(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                    int i = new Random().nextInt(6);
                    if (i == 0){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5 * 20 * 60, 0));
                    }
                    if (i == 1){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5 * 20 * 60, 0));
                    }
                    if (i == 2){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 5 * 20 * 60, 0));
                    }
                    if (i == 3){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5 * 20 * 60, 0));
                    }
                    if (i == 4){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5 * 20 * 60, 1));
                    }
                    if (i == 5){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5 * 20 * 60, 0));
                    }
                    if (i == 6){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5 * 20 * 60, 0));
                    }
                }
        }.runTaskTimer(Main.getInstance(), 0, 5 * 20 * 60);
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
        return "potionswap";
    }

}
