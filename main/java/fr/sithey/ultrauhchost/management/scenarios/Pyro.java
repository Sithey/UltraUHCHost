package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pyro extends CustomScenario {
    @Override
    public String getName() {
        return "Pyro";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.PYRO.getDescription().split(" ")){
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
        return new ItemCreator(Material.FLINT_AND_STEEL).setName(getName() + " : " + Scenario.PYRO.isEnabled()).setAmount(Scenario.PYRO.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void scatterPlayer(Player player) {
        player.getInventory().addItem(new ItemCreator(Material.ENCHANTED_BOOK).addStoredEnchantment(Enchantment.ARROW_FIRE, 1).getItem());
        player.getInventory().addItem(new ItemCreator(Material.ENCHANTED_BOOK).addStoredEnchantment(Enchantment.FIRE_ASPECT, 1).getItem());

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
        return "pyro";
    }
}
