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
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Overcook extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Overcook";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.OVERCOOK.getDescription().split(" ")){
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
        return new ItemCreator(Material.COAL).setName(getName() + " : " + Scenario.OVERCOOK.isEnabled()).setAmount(Scenario.OVERCOOK.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "overcook";
    }
    @EventHandler
    public void onCook(FurnaceSmeltEvent e){
        ItemStack avant = e.getSource();
        ItemStack apres = e.getResult();

        Furnace block = (Furnace) e.getBlock().getState();
        createExplosion(e.getBlock());
        for(ItemStack stack : block.getInventory().getContents()){
            if(stack != null && stack.getType() == avant.getType()){
                stack.setType(apres.getType());
            }
        }
    }

    private void createExplosion(Block b){
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable(){
            @Override
            public void run() {
                b.getLocation().getWorld().createExplosion(b.getLocation(), 5);
            }
        }, 1);
    }
}
