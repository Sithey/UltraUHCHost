package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.*;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Krenzinator extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Krenzinator";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.KRENZINATOR.getDescription().split(" ")){
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
        return new ItemCreator(Material.DIAMOND).setName(getName() + " : " + Scenario.KRENZINATOR.isEnabled()).setAmount(Scenario.KRENZINATOR.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        RuleBoolean.NETHER.setEnabled(false);
        ShapedRecipe recette = new ShapedRecipe(new ItemStack(Material.DIAMOND, 1));
        recette.shape("@@@", "@@@", "@@@");
        recette.setIngredient('@', Material.REDSTONE_BLOCK);
        Bukkit.getServer().addRecipe(recette);
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
        return "krenzinator";
    }

    @EventHandler
    public void onCraft(CraftItemEvent event){
        if (event.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)){
            if (event.getView().getPlayer().getHealth() > 2)
            event.getView().getPlayer().setHealth(event.getView().getPlayer().getHealth() - 2);
            else event.getView().getPlayer().damage(9999);
        }
    }


    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile proj = event.getEntity();

        // check if the projectile is an egg, if not return.
        if (!(proj instanceof Egg) || !(event.getEntity().getPassenger() instanceof Player)) {
            return;
        }
        ((Player) event.getEntity().getPassenger()).damage(1);
    }

}
