package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class FirstHitLove extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "First Hit Love";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.FIRSTHITLOVE.getDescription().split(" ")){
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
        return new ItemCreator(Material.REDSTONE).setName(getName() + " : " + Scenario.FIRSTHITLOVE.isEnabled()).setAmount(Scenario.FIRSTHITLOVE.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        RuleBoolean.FRIENDLYFIRE.setEnabled(false);
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
        return "firsthitlove";
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if(!(e.getDamager() instanceof Player)) return;

        Player p = (Player) e.getDamager();
        Player joueur = (Player) e.getEntity();
            if (UTeam.getTeamWithPlayer(p) == null && UTeam.getTeamWithPlayer(joueur) == null && Time.PVP.getTime() <= 0) {
                e.setCancelled(true);

                p.sendMessage(Message.PREFIX.getMessage() + ChatColor.DARK_PURPLE + "♥ " + ChatColor.GREEN + joueur.getName());
                joueur.sendMessage(Message.PREFIX.getMessage() + ChatColor.DARK_PURPLE + "♥ " + ChatColor.GREEN + p.getName());

                UTeam team = UTeam.createTeam(UPlayer.getUPlayer(p.getUniqueId()));
                UTeam.addPlayerBYPASS(joueur, team);
        }
    }

}
