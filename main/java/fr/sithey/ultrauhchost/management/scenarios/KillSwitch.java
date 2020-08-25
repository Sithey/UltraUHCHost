package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.InventoryUtils;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KillSwitch extends CustomScenario {
    @Override
    public String getName() {
        return "Kill Switch";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.KILLSWITCH.getDescription().split(" ")){
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
        return new ItemCreator(Material.DIAMOND_SWORD).setName(getName() + " : " + Scenario.KILLSWITCH.isEnabled()).setAmount(Scenario.KILLSWITCH.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        if (killer != null){
            killer.getInventory().setArmorContents(player.getInventory().getArmorContents());
            killer.getInventory().setContents(player.getInventory().getContents());
            if (RuleBoolean.GHEAD.isEnabled())
                killer.getWorld().dropItem(killer.getLocation(), new ItemCreator(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").getItem()).setPickupDelay(0);
            if (RuleBoolean.GAPPLE.isEnabled())
                killer.getWorld().dropItem(killer.getLocation(), new ItemCreator(Material.GOLDEN_APPLE).getItem()).setPickupDelay(0);
            InventoryUtils.dropDeath(killer.getLocation());
        }

    }

    @Override
    public String getPath() {
        return "killswitch";
    }
    private int diamond = 0;
    private int gold = 0;


}
