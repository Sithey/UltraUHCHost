package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockRush extends CustomScenario implements Listener {
    private Map<UPlayer, List<Material>> cache;

    @Override
    public String getName() {
        return "Block Rush";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BLOCKRUSH.getDescription().split(" ")){
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
        return new ItemCreator(Material.GOLD_INGOT).setName(getName() + " : " + Scenario.BLOCKRUSH.isEnabled()).setAmount(Scenario.BLOCKRUSH.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
    public void onDeath(final Player player, final Player killer) {
    }

    @Override
    public String getPath() {
        return "blockrush";
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(final BlockBreakEvent event) {
        final UPlayer up = UPlayer.getUPlayer(event.getPlayer().getUniqueId());
        if (this.cache.get(up) != null && this.cache.containsKey(up)) {
            if (!this.cache.get(up).contains(event.getBlock().getType())) {
                event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemCreator(Material.GOLD_INGOT).getItem()).setPickupDelay(0);
            }
        }
        else {
            this.cache.put(up, new ArrayList<>());
            this.cache.get(up).add(event.getBlock().getType());
        }
    }

}
