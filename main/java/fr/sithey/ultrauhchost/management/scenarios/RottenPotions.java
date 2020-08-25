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
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RottenPotions extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Rotten Potions";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.ROTTENPOTIONS.getDescription().split(" ")){
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
        return new ItemCreator(Material.ROTTEN_FLESH).setName(getName() + " : " + Scenario.ROTTENPOTIONS.isEnabled()).setAmount(Scenario.ROTTENPOTIONS.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
            if (event.getItem().getType().equals(Material.ROTTEN_FLESH)) {
                Player player = event.getPlayer();
                player.removePotionEffect(PotionEffectType.HUNGER);
                int chance = new Random().nextInt(100);
                if (chance <= 15){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10 * 20, 0, false, true));
                }else if  (chance <= 25){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0, false, true));
                }else if  (chance <= 35){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10 * 20, 0, false, true));
                }else if  (chance <= 45){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10 * 20, 0, false, true));
                }else if  (chance <= 55){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10 * 20, 0, false, true));
                }else if  (chance <= 65){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10 * 20, 0, false, true));
                }else if  (chance <= 75){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0, false, true));
                }else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * 20, 0, false, true));
                }
            }
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
        return "rottenpotions";
    }
}
