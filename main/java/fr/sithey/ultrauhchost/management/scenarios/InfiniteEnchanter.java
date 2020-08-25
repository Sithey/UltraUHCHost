package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InfiniteEnchanter extends CustomScenario {

    @Override
    public String getName() {
        return "Infinite Enchanter";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.INFINITEENCHANTER.getDescription().split(" ")){
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
        return new ItemCreator(Material.ENCHANTMENT_TABLE).setName(getName() + " : " + Scenario.INFINITEENCHANTER.isEnabled()).setAmount(Scenario.INFINITEENCHANTER.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void scatterPlayer(Player player) {
        player.getInventory().addItem(new ItemCreator(Material.BOOKSHELF).setAmount(128).getItem());
        player.setLevel(99999);
        player.getInventory().addItem(new ItemCreator(Material.ANVIL).setAmount(64).getItem());
        player.getInventory().addItem(new ItemCreator(Material.ENCHANTMENT_TABLE).setAmount(64).getItem());
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
        return "infiniteenchanter";
    }

}
