package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SheepLovers extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Sheep Lovers";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.SHEEPLOVERS.getDescription().split(" ")){
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
        return new ItemCreator(Material.WOOL).setName(getName() + " : " + Scenario.SHEEPLOVERS.isEnabled()).setAmount(Scenario.SHEEPLOVERS.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "sheeplovers";
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
            if (event.getEntityType() == EntityType.SHEEP) {
                event.getDrops().clear();
                Random r = new Random();
                int alea = r.nextInt(100);
                if (alea <= 5) {
                    event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemCreator(Material.GOLD_INGOT).getItem());
                } else {
                    int cooked = r.nextInt(3);
                    event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemCreator(Material.RAW_BEEF).setAmount(cooked).getItem());
                }
            }
    }
}
