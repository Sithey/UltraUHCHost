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
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BenchBlitz extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Bench Blitz";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BENCHBLITZ.getDescription().split(" ")){
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
        return new ItemCreator(Material.WORKBENCH).setName(getName() + " : " + Scenario.BENCHBLITZ.isEnabled()).setAmount(Scenario.BENCHBLITZ.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
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

    }

    @Override
    public String getPath() {
        return "benchblitz";
    }

    private List<UPlayer> cache = new ArrayList<>();
    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
            Player p = (Player) event.getView().getPlayer();

            Material itemType = event.getInventory().getResult().getType();
            if (itemType == null) {
                return;
            }
            if (itemType.equals(Material.WORKBENCH)){
                if (cache.contains(UPlayer.getUPlayer(p.getUniqueId()))){
                    event.getInventory().setResult(new ItemCreator(Material.AIR).getItem());
                }else{
                    cache.add(UPlayer.getUPlayer(p.getUniqueId()));
                }
            }
    }

}
