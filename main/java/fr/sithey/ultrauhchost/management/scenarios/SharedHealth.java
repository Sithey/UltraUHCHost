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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class SharedHealth extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Shared Health";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.SHAREDHEALTH.getDescription().split(" ")){
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
        return new ItemCreator(Material.REDSTONE_BLOCK).setName(getName() + " : " + Scenario.SHAREDHEALTH.isEnabled()).setAmount(Scenario.SHAREDHEALTH.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "sharedhealth";
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if (UTeam.getTeamWithPlayer(player) != null){
                UTeam team = UTeam.getTeamWithPlayer(player);
                for (UPlayer p : team.getAlive()){
                    if (!p.getPlayer().getName().equalsIgnoreCase(player.getName())){
                        p.getPlayer().damage(event.getDamage());
                    }
                }
            }
        }
    }
}
