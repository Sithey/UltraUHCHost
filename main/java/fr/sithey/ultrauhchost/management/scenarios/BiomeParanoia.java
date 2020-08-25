package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class BiomeParanoia extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Biome paranoia";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BIOMEPARANOIA.getDescription().split(" ")){
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
        return new ItemCreator(Material.DIRT).setName(getName() + " : " + Scenario.BIOMEPARANOIA.isEnabled()).setAmount(Scenario.BIOMEPARANOIA.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());

    }

    @Override
    public void scatterPlayer(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                    String name = player.getPlayer().getDisplayName();
                    String biomeColor = ChatColor.translateAlternateColorCodes('&', getBiomeColor(player.getPlayer().getLocation().getBlock().getBiome()));
                    String newPlayerListName = ChatColor.RESET + biomeColor + name;
                    Bukkit.getPlayer(player.getUniqueId()).setPlayerListName(newPlayerListName);
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
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
        return "biomeparanoia";
    }

    private String getBiomeColor(final Biome biome) {
        switch (biome) {
            case BIRCH_FOREST:
            case BIRCH_FOREST_HILLS:
            case BIRCH_FOREST_HILLS_MOUNTAINS:
            case BIRCH_FOREST_MOUNTAINS:
                return "&e&l";
            case EXTREME_HILLS:
            case EXTREME_HILLS_MOUNTAINS:
            case EXTREME_HILLS_PLUS:
            case EXTREME_HILLS_PLUS_MOUNTAINS:
            case SMALL_MOUNTAINS:
            case STONE_BEACH:
                return "&7";
            case JUNGLE:
            case JUNGLE_EDGE:
            case JUNGLE_EDGE_MOUNTAINS:
            case JUNGLE_HILLS:
            case JUNGLE_MOUNTAINS:
                return "&a&l";
            case MEGA_SPRUCE_TAIGA:
            case MEGA_SPRUCE_TAIGA_HILLS:
            case MEGA_TAIGA:
            case MEGA_TAIGA_HILLS:
                return "&3&l";
            case MESA:
            case MESA_BRYCE:
            case MESA_PLATEAU:
            case MESA_PLATEAU_FOREST:
            case MESA_PLATEAU_FOREST_MOUNTAINS:
            case MESA_PLATEAU_MOUNTAINS:
                return "&4";
            case SAVANNA:
            case SAVANNA_MOUNTAINS:
            case SAVANNA_PLATEAU:
            case SAVANNA_PLATEAU_MOUNTAINS:
                return "&6";
            case COLD_TAIGA:
            case COLD_TAIGA_HILLS:
            case COLD_TAIGA_MOUNTAINS:
                return "&9";
            case TAIGA:
            case TAIGA_HILLS:
            case TAIGA_MOUNTAINS:
                return "&3";
            case DEEP_OCEAN:
            case OCEAN:
            case FROZEN_OCEAN:
                return "&1";
            case DESERT:
            case DESERT_HILLS:
            case DESERT_MOUNTAINS:
                return "&e";
            case FLOWER_FOREST:
            case FOREST:
            case FOREST_HILLS:
                return "&2";
            case BEACH:
            case COLD_BEACH:
                return "&e&o";
            case RIVER:
            case FROZEN_RIVER:
                return "&b";
            case PLAINS:
            case SUNFLOWER_PLAINS:
                return "&a";
            case SWAMPLAND:
            case SWAMPLAND_MOUNTAINS:
                return "&8";
            case ROOFED_FOREST:
            case ROOFED_FOREST_MOUNTAINS:
                return "&2&l";
            case MUSHROOM_ISLAND:
            case MUSHROOM_SHORE:
                return "&7&o";
            case ICE_MOUNTAINS:
            case ICE_PLAINS:
                return "&f&l";
            case ICE_PLAINS_SPIKES:
                return "&f&o";
            case HELL:
                return "&c";
            case SKY:
                return "&0";
            default:
                return "&f";
        }
    }




}
