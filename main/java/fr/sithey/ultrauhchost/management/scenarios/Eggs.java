package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Eggs extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Eggs";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.EGGS.getDescription().split(" ")){
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
        return new ItemCreator(Material.EGG).setName(getName() + " : " + Scenario.EGGS.isEnabled()).setAmount(Scenario.EGGS.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "eggs";
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile proj = event.getEntity();

        // check if the projectile is an egg, if not return.
        if (!(proj instanceof Egg)) {
            return;
        }

        Random rand = new Random();

        // make a list of all the entitytypes
        ArrayList<EntityType> types = new ArrayList<EntityType>();

        // Loop over all entity types.
        for (EntityType type : EntityType.values()) {
            // if the current looped type isnt alive AND isn't spawnable, hop over this loop.
            if (!type.isAlive() || !type.isSpawnable()) {
                continue;
            }

            // add the loop value to the list.
            types.add(type);
        }

        // get a random type out of the list we just created.
        EntityType type = types.get(rand.nextInt(types.size()));

        // get the location and world the projectile hit in.
        Location loc = proj.getLocation();
        World world = proj.getWorld();

        // spawn the entity in the world and location we got.
        world.spawnEntity(loc, type);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        // check if the entity was a chicken, if not return.
        if (!(entity instanceof Chicken)) {
            return;
        }

        Random rand = new Random();
        double chance = 0.05;

        // check if the random value is more than the chance, if so return.
        if (rand.nextDouble() > chance) {
            return;
        }

        // add an egg to the drops of the chicken.
        event.getDrops().add(new ItemStack(Material.EGG));
    }

}
