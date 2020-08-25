package fr.sithey.ultrauhchost.management;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.command.*;
import fr.sithey.ultrauhchost.lib.Files;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.lib.TwitterManager;
import fr.sithey.ultrauhchost.listener.*;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleList;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.WhiteList;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class UHC {
    private Files message;
    private Files configuration;
    private Location spawn;
    private Statut statut;
    private TwitterManager twitterManager;
    public UHC(Main main){
        message = new Files("message.yml");
        configuration = new Files("configuration.yml");

        loadFiles();
        loadWorld();
        loadCommandes(main);
        loadListeners(main);

        WhiteList whitelist = MinecraftServer.getServer().getPlayerList().getWhitelist();
        whitelist.getValues().clear();
        Bukkit.getServer().setWhitelist(true);

        setStatut(Statut.WAIT);
    }

    public Files getConfiguration() {
        return configuration;
    }

    public Files getMessage() {
        return message;
    }

    public Statut getStatut() {
        return statut;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    private void loadFiles(){
        configuration.addValue("spawn.world", "world");
        configuration.addValue("spawn.x", 0.5);
        configuration.addValue("spawn.y", 100);
        configuration.addValue("spawn.z", 0.5);
        configuration.addValue("spawn.yaw", 0);
        configuration.addValue("spawn.pitch", 0);
        new WorldCreator(configuration.getConfig().getString("spawn.world")).createWorld();
        spawn = new Location(Bukkit.getWorld(configuration.getConfig().getString("spawn.world")), configuration.getConfig().getDouble("spawn.x"),
                configuration.getConfig().getDouble("spawn.y"), configuration.getConfig().getDouble("spawn.z"),
                configuration.getConfig().getInt("spawn.yaw"), configuration.getConfig().getInt("spawn.pitch"));
        configuration.addValue("times.damage", 30);
        Time.DAMAGE.setTime(configuration.getConfig().getInt("times.damage"));
        configuration.addValue("times.finalheal", 3 * 60);
        Time.FINALHEAL.setTime(configuration.getConfig().getInt("times.finalheal"));
        configuration.addValue("times.pvp", 20 * 60);
        Time.PVP.setTime(configuration.getConfig().getInt("times.pvp"));
        configuration.addValue("times.meetup", 45 * 60);
        Time.MEETUP.setTime(configuration.getConfig().getInt("times.meetup"));
        configuration.addValue("host.join", false);
        configuration.addValue("twitter.enabled", false);
        configuration.addValue("twitter.post", Arrays.asList(
                "Ultra UHC Host",
                "",
                "<type> <scenarios>",
                "ip: mc.cobalduhc.com",
                "Open: <hour>"
        ));
        configuration.addValue("twitter.autovictory.enabled", false);
        configuration.addValue("twitter.autovictory.post", Collections.singletonList(
                "Victory to <players> for winning this uhc with <kills> kills"
        ));
        configuration.addValue("twitter.consumer_key", "");
        TwitterManager.consumer_key = configuration.getValue("twitter.consumer_key");
        configuration.addValue("twitter.consumer_secret", "");
        TwitterManager.consumer_secret = configuration.getValue("twitter.consumer_secret");
        configuration.addValue("twitter.accesstoken", "");
        TwitterManager.accesstoken = configuration.getValue("twitter.accesstoken");
        configuration.addValue("twitter.accesstoken_secret", "");
        TwitterManager.accesstoken_secret = configuration.getValue("twitter.accesstoken_secret");

        if (configuration.getConfig().getBoolean("twitter.enabled")){
            twitterManager = new TwitterManager(Main.getInstance());
            twitterManager.connectToTwitter();
        }

        configuration.addValue("cleancenter.enabled", false);
        configuration.addValue("cleancenter.radius", 50);

        configuration.addValue("autorestart.enabled", false);
        configuration.addValue("autorestart.time", 360);
        message.addValue("chat.enabled", true);
        message.addValue("chat.format", "&8<<player>&8> &7: &f<chat>");
        Message.CHAT.setMessage(message.getValue("chat.format"));
        message.addValue("chat.host", "&7[&6HOST&7] &6");
        Message.HOST.setMessage(message.getValue("chat.host"));
        message.addValue("chat.spec", "&7[&eSPEC&7] &e");
        Message.SPEC.setMessage(message.getValue("chat.spec"));
        message.addValue("chat.death", "&7[&bDEATH&7] &b");
        Message.DEATH.setMessage(message.getValue("chat.death"));

        message.addValue("prefix", "&8[&eUHC&8] &c");
        Message.PREFIX.setMessage(message.getValue("prefix"));

        message.addValue("login.join", "&e<player> join the game.");
        Message.JOIN.setMessage(message.getValue("login.join"));
        message.addValue("login.quit", "&e<player> left the game.");
        Message.QUIT.setMessage(message.getValue("login.quit"));
        message.addValue("login.welcome", "&eThanks to using UltraUHCHost plugin, if you see any error contact me at Twitter &b&l@SitheyMC/n&c(this message is editable on message.yml).");
        Message.WELCOME.setMessage(message.getValue("login.welcome"));

        message.addValue("scatter.starting", "&eStarting to scatter <online> players");
        Message.SCATTERSTARTING.setMessage(message.getValue("scatter.starting"));
        message.addValue("scatter.finish", "&eScatter finish, starting soon.");
        Message.SCATTERFINISH.setMessage(message.getValue("scatter.finish"));

        message.addValue("damage.in", "&eActive damage in <timesec> seconds.");
        Message.DAMAGEIN.setMessage(message.getValue("damage.in"));
        message.addValue("damage.now", "&eYou are now taking damage.");
        Message.DAMAGENOW.setMessage(message.getValue("damage.now"));
        message.addValue("finalheal.in", "&eFinal heal in <timemin> minutes and <timesec> seconds.");
        Message.FINALHEALIN.setMessage(message.getValue("finalheal.in"));
        message.addValue("finalheal.now", "&eYou have been regenerated, don't asking another healing.");
        Message.FINALHEALNOW.setMessage(message.getValue("finalheal.now"));
        message.addValue("pvp.in", "&ePvP in <timemin> minutes and <timesec> seconds.");
        Message.PVPIN.setMessage(message.getValue("pvp.in"));
        message.addValue("pvp.now", "&ePvP now active.");
        Message.PVPNOW.setMessage(message.getValue("pvp.now"));
        message.addValue("meetup.in", "&eMeetup in <timemin> minutes and <timesec> seconds.");
        Message.MEETUPIN.setMessage(message.getValue("meetup.in"));
        message.addValue("meetup.now", "&eMeetup now active, it's not allowed to stay mining./n&eThe border shrink in <finalborder> on 20 minutes");
        Message.MEETUPNOW.setMessage(message.getValue("meetup.now"));

        message.addValue("died.self", "&f<player> died");
        Message.DIEDSELF.setMessage(message.getValue("died.self"));
        message.addValue("died.killed", "&f<player> was slain by <killer>");
        Message.DIEDKILLED.setMessage(message.getValue("died.killed"));

        message.addValue("victory", "&aVictory to <winners> with <kills>");
        Message.VICTORY.setMessage(message.getValue("victory"));

        Message.SCATTERSTARTING.setMessage(message.getValue("scatter.starting"));
        message.addValue("scatter.finish", "&eScatter finish, starting soon.");
        Message.SCATTERFINISH.setMessage(message.getValue("scatter.finish"));

        message.addValue("scoreboard.title", "&eUHC &6&o#<uhcnumber>");
        Message.SCOREBOARDTITLE.setMessage(message.getValue("scoreboard.title"));

        message.addValue("latescatter.notify", "&eUse /latescatter to be latescattered");
        Message.LATESCATTERJOIN.setMessage(message.getValue("latescatter.notify"));

        message.addValue("latescatter.broadcast", "&a<player> just been latescattered");
        Message.LATESCATTERBROADCAST.setMessage(message.getValue("latescatter.broadcast"));

        message.addValue("scoreboard.lobby", Arrays.asList(
                "&8&m---------------",
                "&7Player: &c<online>/<slots>",
                "&8&m---------------",
                "&7Host: &c<host>",
                "&8&m---------------",
                "&c@SitheyMC"
        ));
        RuleList.SCOREBOARDWAIT.setValue(message.getConfig().getStringList("scoreboard.lobby"));
        message.addValue("scoreboard.scatter", Arrays.asList(
                "&8&m---------------",
                "&7Scatter: &c<scatter>/<online>",
                "&8&m---------------",
                "&c@SitheyMC"
        ));
        RuleList.SCOREBOARDSCATTER.setValue(message.getConfig().getStringList("scoreboard.scatter"));
        message.addValue("scoreboard.game", Arrays.asList(
                "&8&m---------------",
                "&7Player: &c<online>/<slots>",
                "&8&m---------------",
                "&7Time: &c<time>",
                "&8&m---------------",
                "&7PvP: &c<pvp>",
                "&7Meetup: &c<meetup>",
                "&8&m---------------",
                "&7Kills: &c<kills>",
                "&8&m---------------",
                "&7Border: &c<border>",
                "&8&m---------------",
                "&c@SitheyMC"
        ));
        RuleList.SCOREBOARDGAME.setValue(message.getConfig().getStringList("scoreboard.game"));

        message.addValue("helpop.send", "&eHelpop has been sent.");
        Message.HELPOPSENT.setMessage(message.getValue("helpop.send"));
        message.addValue("helpop.receive", "&6Helpop from &a<player> &7:&c <text>");
        Message.HELPOPRECEIVE.setMessage(message.getValue("helpop.receive"));
        message.addValue("tablist.header", "&4&lUltraUHCHost\n");
        Message.HEADER.setMessage(message.getValue("tablist.header"));
        message.addValue("tablist.footer", "\n&cPlugin made by &b&l@SitheyMC");
        Message.FOOTER.setMessage(message.getValue("tablist.footer"));
        if (!Main.getInstance().isPremium()){
            Message.HEADER.setMessage("§eUltraUHCHost");
            Message.FOOTER.setMessage("§cPlugin made by §b§l@SitheyMC");
        }

        for (Scenario scenario : Scenario.values()){
            message.addValue("scenarios." + scenario.getScenario().getPath(), scenario.getDescription());
            scenario.setDescription(message.getValue("scenarios." + scenario.getScenario().getPath()));
        }
    }

    private void loadWorld(){
        for (World world : Bukkit.getWorlds()){
            world.setPVP(false);
            world.setTime(0);
            world.setDifficulty(Difficulty.NORMAL);
            world.getWorldBorder().setCenter(0, 0);
            world.getWorldBorder().setSize(2000);
            world.getWorldBorder().setDamageBuffer(1);
            world.getWorldBorder().setWarningTime(10);
            world.getWorldBorder().setWarningDistance(0);
            world.getWorldBorder().setDamageAmount(0.2);
            world.setGameRuleValue("naturalRegeneration", "false");
            world.setGameRuleValue("doFireTick", "false");
        }

        if (Main.getInstance().isPremium() && getConfiguration().getConfig().getBoolean("cleancenter.enabled")){
            new BukkitRunnable() {
                @Override
                public void run() {
                    prepareSpawn(Bukkit.getWorld("world"), getConfiguration().getConfig().getInt("cleancenter.radius"));
                }
            }.runTaskLater(Main.getInstance(), 100);
        }
    }

    private void loadListeners(Main main){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerLog(), main);
        pm.registerEvents(new PlayerDamage(), main);
        pm.registerEvents(new PlayerLobby(), main);
        pm.registerEvents(new PlayerInventory(), main);
        pm.registerEvents(new PlayerWorld(), main);
        pm.registerEvents(new PlayerItems(), main);
    }

    private void loadCommandes(Main main){
        main.getCommand("bypass").setExecutor(new HostCMD());
        main.getCommand("heal").setExecutor(new HostCMD());
        main.getCommand("finishuhc").setExecutor(new HostCMD());
        main.getCommand("mutechat").setExecutor(new HostCMD());
        main.getCommand("setnumber").setExecutor(new HostCMD());
        main.getCommand("tpto").setExecutor(new HostCMD());
        main.getCommand("rtp").setExecutor(new HostCMD());
        main.getCommand("sethost").setExecutor(new HostCMD());
        main.getCommand("spec").setExecutor(new HostCMD());
        main.getCommand("team").setExecutor(new TeamCMD());
        main.getCommand("helpop").setExecutor(new HelpopCMD());
        main.getCommand("scenarios").setExecutor(new RulesCMD());
        main.getCommand("rules").setExecutor(new RulesCMD());
        main.getCommand("saveitems").setExecutor(new HostCMD());
        main.getCommand("twitter").setExecutor(new HostCMD());
        main.getCommand("revive").setExecutor(new HostCMD());
        main.getCommand("moveteam").setExecutor(new HostCMD());
        main.getCommand("latescatter").setExecutor(new LatescatterCMD());
    }

    public void deleteWorld(File path) {
        System.out.println("Deleting... world");

        if (path.exists()) {
            File[] files = path.listFiles();

            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
    }

    public TwitterManager getTwitterManager() {
        return twitterManager;
    }

    private void prepareSpawn(final World world, int r) {
        new BukkitRunnable(){
            int yInicial = 40;
            int progress = 0;
            int YChange = this.yInicial;

            public void run() {
                boolean xM = false;
                boolean zM = false;
                int radius = r;
                for (int x = 0 - radius; x <= 0 + radius; ++x) {
                    for (int z = 0 - radius; z <= 0 + radius; ++z) {
                        Block block = world.getBlockAt(x, this.YChange, z);
                        if (block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2 || block.getType() == Material.LOG || block.getType() == Material.LOG_2) {
                            block.setType(Material.AIR);
                            continue;
                        }
                        if (block.getType() != Material.WATER && block.getType() != Material.STATIONARY_WATER) continue;
                        block.setType(Material.GRASS);
                    }
                }
                ++this.YChange;
                ++this.progress;
                if (this.progress >= 50) {
                    this.cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 1L, 1L);
    }
}
