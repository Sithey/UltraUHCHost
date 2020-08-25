package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CraftingTeleportation extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Crafting Teleportation";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.CRAFTINGTELEPORTION.getDescription().split(" ")){
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
        return new ItemCreator(Material.ENDER_PEARL).setName(getName() + " : " + Scenario.CRAFTINGTELEPORTION.isEnabled()).setAmount(Scenario.CRAFTINGTELEPORTION.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "craftingteleportation";
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getItem() == null) return;
        if(e.getItem().getType() != Material.ENDER_PEARL) return;

        ItemStack item = e.getItem();
        Player user =  e.getPlayer();

        for(UPlayer p : UPlayer.getAlivePlayers()){
            if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equalsIgnoreCase(p.getPlayer().getName())){
                e.setCancelled(true);

                Location loc = p.getPlayer().getLocation();
                int nombreX = (loc.getBlockX() - 50) + (new Random().nextInt(100));
                int nombreZ = (loc.getBlockZ() - 50) + (new Random().nextInt(100));
                int nombreY = p.getPlayer().getLocation().getWorld().getHighestBlockAt(nombreX, nombreZ).getLocation().getBlockY();

                Location newLoc = new Location(loc.getWorld(), nombreX, nombreY, nombreZ);

                user.teleport(newLoc);

                item.setAmount(1);
                user.getInventory().removeItem(item);
                user.updateInventory();
            }
        }
    }
}
