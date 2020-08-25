package fr.sithey.ultrauhchost.lib;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CustomScenario {

    public abstract String getName();

    public ItemStack getItem(){
        return null;
    };

    public abstract void onStart();

    public abstract void scatterPlayer(Player player);

    public abstract void onPvP();

    public abstract void onMeetup();

    public abstract void onDeath(Player player, Player killer);

    public abstract String getPath();

}
