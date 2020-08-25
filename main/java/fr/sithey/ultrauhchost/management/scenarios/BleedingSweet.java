package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Material.*;

public class BleedingSweet extends CustomScenario{

    @Override
    public String getName() {
        return "Bleeding Sweet";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BLEEDINGSWEET.getDescription().split(" ")){
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
        return new ItemCreator(DIAMOND).setName(getName() + " : " + Scenario.BLEEDINGSWEET.isEnabled()).setAmount(Scenario.BLEEDINGSWEET.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

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
        player.getWorld().dropItem(player.getLocation(), new ItemCreator(DIAMOND).getItem());
        player.getWorld().dropItem(player.getLocation(), new ItemCreator(ARROW).setAmount(16).getItem());
        player.getWorld().dropItem(player.getLocation(), new ItemCreator(STRING).setAmount(2).getItem());
        player.getWorld().dropItem(player.getLocation(), new ItemCreator(GOLD_INGOT).setAmount(5).getItem());
    }

    @Override
    public String getPath() {
        return "bleedingsweet";
    }

}
