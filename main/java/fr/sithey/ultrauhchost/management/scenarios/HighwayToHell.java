package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HighwayToHell extends CustomScenario {

    @Override
    public String getName() {
        return "Highway To Hell";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.HIGHWAYTOHELL.getDescription().split(" ")){
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
        return new ItemCreator(Material.OBSIDIAN).setName(getName() + " : " + Scenario.HIGHWAYTOHELL.isEnabled()).setAmount(Scenario.HIGHWAYTOHELL.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        RuleBoolean.NETHER.setEnabled(true);
        RuleBoolean.TRAP.setEnabled(false);
    }

    @Override
    public void scatterPlayer(Player player) {
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.OBSIDIAN).setAmount(14).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.FLINT_AND_STEEL).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.LAVA_BUCKET).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.IRON_PICKAXE).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.COOKED_BEEF).setAmount(64).getItem());
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
        return "highwaytohell";
    }

}
