package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Armageddon extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Armageddon";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.ARMAGEDDON.getDescription().split(" ")){
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
        return new ItemCreator(Material.REDSTONE).setName(getName() + " : " + Scenario.ARMAGEDDON.isEnabled()).setAmount(Scenario.ARMAGEDDON.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        new BukkitRunnable() {
            @Override
            public void run() {
                dropArmageddonItem();
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void PotionSplashEvent(final PotionSplashEvent event) {
        if (event.getPotion().getShooter() instanceof Player) {
            final LivingEntity source = ((Player) event.getPotion().getShooter());
            if (source != null && source.toString() != null && source.toString().equalsIgnoreCase("CraftChicken")) {
                source.setHealth(0.0);
            }
        }
    }

    private void dropArmageddonItem() {
        final Chunk[] chunkscandidates = Bukkit.getWorld("world").getLoadedChunks();
        final Random r = new Random();
        if (chunkscandidates.length > 0) {
            final Chunk dropChunk = chunkscandidates[r.nextInt(chunkscandidates.length)];
            final Location dropFrom = new Location(dropChunk.getWorld(), (double)(dropChunk.getX() * 16 + r.nextInt(16)), 255.0, (double)(dropChunk.getZ() * 16 + r.nextInt(15)));
            this.handlePotionDrop(dropChunk, dropFrom);
            final int randomInt = r.nextInt(100);
            if (randomInt > 90) {
                final int itemChoice = r.nextInt(10);
                switch (itemChoice) {
                    case 0: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.GOLD_INGOT, 1));
                    }
                    case 1: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.DIAMOND, 1));
                    }
                    case 2: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.NETHER_WARTS, 1));
                    }
                    case 3: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.BLAZE_ROD, 1));
                    }
                    case 4: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.STRING, 3));
                    }
                    case 5: {
                        dropChunk.getWorld().spawnFallingBlock(dropFrom, Material.MELON_BLOCK, (byte)0);
                    }
                    case 6: {
                        dropChunk.getWorld().spawnFallingBlock(dropFrom, Material.IRON_BLOCK, (byte)0);
                    }
                    case 7: {
                        dropChunk.getWorld().spawnFallingBlock(dropFrom, Material.MOB_SPAWNER, (byte)0);
                    }
                    case 8: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.BLAZE_POWDER, 1));
                    }
                    case 9: {
                        dropChunk.getWorld().dropItem(dropFrom, new ItemStack(Material.BOOK, 1));
                        break;
                    }
                }
            }
            else if (randomInt > 70) {
                this.handlePotionDrop(dropChunk, dropFrom);
            }
            else if (randomInt > 40) {
                dropChunk.getWorld().spawnFallingBlock(dropFrom, r.nextBoolean() ? Material.SAND : Material.GRAVEL, (byte)0);
            }
            else {
                dropChunk.getWorld().spawnFallingBlock(dropFrom, Material.LAVA, (byte)0);
            }
        }
    }

    public void handlePotionDrop(final Chunk dropChunk, final Location dropFrom) {
        final Random r = new Random();
        final int itemChoice = r.nextInt(30);
        if (itemChoice > 20) {
            this.dropPotion(dropChunk, dropFrom, PotionType.INSTANT_DAMAGE, r.nextInt(2));
        }
        else if (itemChoice > 15) {
            this.dropPotion(dropChunk, dropFrom, PotionType.POISON, r.nextInt(2));
        }
        else if (itemChoice > 10) {
            this.dropPotion(dropChunk, dropFrom, PotionType.WEAKNESS, r.nextInt(2));
        }
        else if (itemChoice > 5) {
            this.dropPotion(dropChunk, dropFrom, PotionType.SLOWNESS, r.nextInt(2));
        }
        else {
            final PotionType[] types = PotionType.values();
            this.dropPotion(dropChunk, dropFrom, types[r.nextInt(types.length)], r.nextInt(2));
        }
    }

    private void dropPotion(final Chunk dropChunk, final Location dropFrom, final PotionType type, final int modifier) {
        if (type == PotionType.WATER) {
            return;
        }
        final Potion potion = new Potion(type, 1 + modifier);
        potion.setSplash(true);
        if (potion.getType() != PotionType.INSTANT_DAMAGE && potion.getType() != PotionType.INSTANT_HEAL) {
            potion.extend();
        }
        final ItemStack itemStack = new ItemStack(Material.POTION);
        potion.apply(itemStack);
        final LivingEntity e = (LivingEntity)dropChunk.getWorld().spawnEntity(dropFrom, EntityType.CHICKEN);
        final ThrownPotion tp = e.launchProjectile(ThrownPotion.class, new Vector(0, -1, 0));
        tp.setItem(itemStack);
        tp.setVelocity(new Vector(0, -1, 0));
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
        return "armagedon";
    }


}
