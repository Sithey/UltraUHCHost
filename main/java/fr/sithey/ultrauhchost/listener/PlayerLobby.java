package fr.sithey.ultrauhchost.listener;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerLobby implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event){
        if (Main.getInstance().getUHC().getStatut().isLobby() || !UPlayer.getUPlayer(event.getEntity().getUniqueId()).isPlaying())
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if (Main.getInstance().getUHC().getStatut().isLobby() || !UPlayer.getUPlayer(event.getPlayer().getUniqueId()).isPlaying())
            event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        if (Main.getInstance().getUHC().getStatut().isLobby() || !UPlayer.getUPlayer(event.getPlayer().getUniqueId()).isPlaying())
            event.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if ((Main.getInstance().getUHC().getStatut().isLobby()  || !UPlayer.getUPlayer(event.getPlayer().getUniqueId()).isPlaying())&& !event.getPlayer().hasPermission("ultrauhc.admin"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if ((Main.getInstance().getUHC().getStatut().isLobby()  || !UPlayer.getUPlayer(event.getPlayer().getUniqueId()).isPlaying())&& !event.getPlayer().hasPermission("ultrauhc.admin"))
            event.setCancelled(true);
    }
}
