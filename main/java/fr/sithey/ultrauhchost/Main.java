package fr.sithey.ultrauhchost;

import fr.sithey.ultrauhchost.lib.BiomeReplacer;
import fr.sithey.ultrauhchost.lib.JsonMessage;
import fr.sithey.ultrauhchost.lib.PatchBiome;
import fr.sithey.ultrauhchost.management.Gui;
import fr.sithey.ultrauhchost.management.UHC;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    private UHC uhc;
    private Gui gui;
    private boolean premium = false;

    @Override
    public void onEnable() {
        instance = this;
        this.uhc = new UHC(this);
        this.gui = new Gui();
        Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getPluginManager().callEvent(new PlayerJoinEvent(p, "")));
    }

    @Override
    public void onDisable() {
        UTeam.reset();
    }

    @Override
    public void onLoad() {
        BiomeReplacer.init();
        if (isPremium()) {
            try {
                new PatchBiome().patchBiomes();
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public UHC getUHC() {
        return uhc;
    }

    public Gui getGui() {
        return gui;
    }

    public boolean isPremium() {
        return premium;
    }

    public void sendPremiumMessage(Player player){
        new JsonMessage().append("§bYou need to purchase §4§lPREMIUM §bto do this. Click here.").setClickAsURL("https://www.mc-market.org/resources/14876/").save().send(player);
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 2f, 2f);
    }
}
