package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SuperHeroesPlus extends CustomScenario {

    @Override
    public String getName() {
        return "Super Heroes +";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.SUPERHEROESPLUS.getDescription().split(" ")){
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
        return new ItemCreator(Material.BLAZE_ROD).setName(getName() + " : " + Scenario.SUPERHEROESPLUS.isEnabled()).setAmount(Scenario.SUPERHEROESPLUS.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void scatterPlayer(Player player) {
        Random ran = new Random();
        int aleatoire = ran.nextInt(5);
        if (aleatoire == 0){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1, false, false));
        }
        if (aleatoire == 1){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, false, false));
        }
        if (aleatoire == 2){
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999, 0, false, false));
        }
        if (aleatoire == 3){
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 3, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 99999, 1, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 99999, 9, false, false));
        }
        if (aleatoire == 4){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 99999, 0, false, false));
        }
        if (aleatoire == 5){
            player.setMaxHealth(player.getMaxHealth() + 20);
            player.setHealth(player.getMaxHealth());
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
        return "superheroes+";
    }

}
