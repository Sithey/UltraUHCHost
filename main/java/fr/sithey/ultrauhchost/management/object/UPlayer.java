package fr.sithey.ultrauhchost.management.object;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.host.Inventory;
import fr.sithey.ultrauhchost.lib.InventoryUtils;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.lib.ScoreboardLife;
import fr.sithey.ultrauhchost.lib.Tablist;
import fr.sithey.ultrauhchost.lib.sb.FastBoard;
import fr.sithey.ultrauhchost.management.UHC;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleList;
import fr.sithey.ultrauhchost.management.task.Scatter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class UPlayer {
    private String uuid;
    private int kills, diamonds, golds, irons, redstones, emeralds, lapis;
    private boolean playing, canlatescatter;
    private FastBoard board;
    private ItemStack[] inventory = new ItemStack[40];
    private Location death;

    public UPlayer(String uuid){
        this.uuid = uuid;
        this.kills = 0; this.diamonds = 0; this.golds = 0; this.irons = 0; this.redstones = 0; this.emeralds = 0; this.lapis = 0;
        this.playing = false; this.canlatescatter = true;
        death = null;
        UPlayer.getPlayers.add(this);
    }

    public UUID getUniqueId() {
        return UUID.fromString(uuid);
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(getUniqueId());
    }

    public OfflinePlayer getOfflinePlayer(){
        return Bukkit.getOfflinePlayer(getUniqueId());
    }

    public int getKills() {
        return kills;
    }

    public void addKill(){
        kills++;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void addDiamond(){
        diamonds++;
    }

    public int getGolds() {
        return golds;
    }

    public void addGold(){
        golds++;
    }

    public int getIrons() {
        return irons;
    }

    public void addIron(){
        irons++;
    }

    public int getEmeralds() {
        return emeralds;
    }

    public void addEmerald(){
        emeralds++;
    }

    public int getLapis() {
        return lapis;
    }

    public void addLapis(){
        lapis++;
    }

    public int getRedstones() {
        return redstones;
    }

    public void addRedstone() {
        redstones++;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isCanlatescatter() {
        return canlatescatter;
    }

    public void setCanlatescatter(boolean canlatescatter) {
        this.canlatescatter = canlatescatter;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Location getDeath() {
        return death;
    }

    public void destroy(){
        UPlayer.getPlayers.remove(this);
    }

    public void setLobby(){
        if (getPlayer() == null)
            return;
        setPlaying(true);
        setCanlatescatter(false);
        getPlayer().setGameMode(GameMode.ADVENTURE);
        getPlayer().setMaxHealth(20);
        getPlayer().setHealth(20);
        getPlayer().setFoodLevel(20);
        getPlayer().setExp(0);
        getPlayer().setLevel(0);
        getPlayer().setMaximumNoDamageTicks(20);
        getPlayer().getInventory().clear();
        getPlayer().getInventory().setArmorContents(null);
        getPlayer().getInventory().setItem(0, new ItemCreator(Material.BOOK).setName("§7● §cRules").getItem());
        for (PotionEffect effect : getPlayer().getActivePotionEffects())
            getPlayer().removePotionEffect(effect.getType());

        if (UPlayer.getHost == null && Main.getInstance().getUHC().getConfiguration().getConfig().getBoolean("host.join")){
            UPlayer.getHost = this;
        }

        if (UPlayer.getHost == this || getPlayer().hasPermission("ultrauhc.host")){
            getPlayer().getInventory().setItem(8, new ItemCreator(Material.ENCHANTED_BOOK).setName("§7● §4Configuration").getItem());
        }
        getPlayer().sendMessage(Message.WELCOME.getMessage());
        new BukkitRunnable() {
            @Override
            public void run() {
                getPlayer().teleport(Main.getInstance().getUHC().getSpawn());
                getPlayer().playSound(getPlayer().getLocation(), Sound.LEVEL_UP, 2f, 2f);
            }
        }.runTaskLater(Main.getInstance(), 2);
    }

    public void respawn(){
        getPlayer().teleport(death);
        getPlayer().setGameMode(GameMode.SURVIVAL);
        setPlaying(true);

        getPlayer().getInventory().clear();
        getPlayer().getInventory().setHelmet(null);
        getPlayer().getInventory().setChestplate(null);
        getPlayer().getInventory().setLeggings(null);
        getPlayer().getInventory().setBoots(null);

        for (int slot = 0; slot < 36; slot++) {
            ItemStack item = inventory[slot];
            if (item != null) {
                getPlayer().getInventory().setItem(slot, item);
            }
        }

        getPlayer().getInventory().setHelmet(inventory[36]);
        getPlayer().getInventory().setChestplate(inventory[37]);
        getPlayer().getInventory().setLeggings(inventory[38]);
        getPlayer().getInventory().setBoots(inventory[39]);
    }

    public void setDeath(UPlayer killer){
        Location location = getPlayer().getLocation();
        getPlayer().getWorld().strikeLightningEffect(location);
        death = location;
        for (int slot = 0; slot < 36; slot++) {
            ItemStack item = getPlayer().getInventory().getItem(slot);
            if (item != null) {
                inventory[slot] = item;
            }
        }

        inventory[36] = getPlayer().getInventory().getHelmet();
        inventory[37] = getPlayer().getInventory().getChestplate();
        inventory[38] = getPlayer().getInventory().getLeggings();
        inventory[39] = getPlayer().getInventory().getBoots();


        if (!Scenario.KILLSWITCH.isEnabled() && !Scenario.TIMEBOMB.isEnabled()) {
            if (RuleBoolean.GHEAD.isEnabled())
                getPlayer().getWorld().dropItem(location, new ItemCreator(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").getItem());
            if (RuleBoolean.GAPPLE.isEnabled())
                getPlayer().getWorld().dropItem(location, new ItemCreator(Material.GOLDEN_APPLE).getItem());
            getPlayer().getInventory().forEach(itemStack -> {
                if (itemStack != null)
                    location.getWorld().dropItem(location, itemStack);
            });
            if (getPlayer().getInventory().getHelmet() != null)
            location.getWorld().dropItem(location, getPlayer().getInventory().getHelmet());
            if (getPlayer().getInventory().getChestplate() != null)
            location.getWorld().dropItem(location, getPlayer().getInventory().getChestplate());
            if (getPlayer().getInventory().getLeggings() != null)
            location.getWorld().dropItem(location, getPlayer().getInventory().getLeggings());
            if (getPlayer().getInventory().getBoots() != null)
            location.getWorld().dropItem(location, getPlayer().getInventory().getBoots());

            InventoryUtils.dropDeath(location);
        }

        getPlayer().getWorld().spawn(location, ExperienceOrb.class).setExperience(getPlayer().getExpToLevel());
        if (RuleBoolean.HEAD.isEnabled()){
            Block block = location.getBlock().getRelative(BlockFace.UP);
            block.setType(Material.SKULL);
            getPlayer().getLocation().getBlock().setType(Material.NETHER_FENCE);
            block.setData((byte)0x1);
            BlockState state = block.getState();
            if(state instanceof Skull) {
                Skull skull = (Skull)state;
                skull.setRotation(BlockFace.NORTH);
                skull.setOwner(getPlayer().getName());
                skull.setSkullType(SkullType.PLAYER);
                skull.update();
            }
        }


        if (killer != null && killer.getPlayer() != null){
            for (Scenario scenario : Scenario.getEnabled()){
                scenario.getScenario().onDeath(getPlayer(), killer.getPlayer());
            }
            killer.addKill();
            killer.getPlayer().playSound(killer.getPlayer().getLocation(), Sound.LEVEL_UP, 2f, 2f);
            Bukkit.broadcastMessage(Message.DIEDKILLED.getMessage().replace("<player>", getPlayer().getName()).replace("<killer>",killer.getPlayer().getName()));
        }else{
            for (Scenario scenario : Scenario.getEnabled()){
                scenario.getScenario().onDeath(getPlayer(), null);
            }
            Bukkit.broadcastMessage(Message.DIEDSELF.getMessage().replace("<player>", getPlayer().getName()));
        }

        getPlayer().getInventory().clear();
        getPlayer().getInventory().setArmorContents(null);
        if (getPlayer().hasPermission("ultrauhc.spec") || RuleBoolean.SPEC.isEnabled()){
            getPlayer().setGameMode(GameMode.SPECTATOR);
        }else{
            getPlayer().setGameMode(GameMode.ADVENTURE);
            getPlayer().teleport(Main.getInstance().getUHC().getSpawn());
        }

        setPlaying(false);

        if (Main.getInstance().getUHC().getStatut().equals(Statut.GAME) && (RuleInteger.TEAMSIZE.getValue() == 1 && UPlayer.getAlivePlayers().size() == 1) || (RuleInteger.TEAMSIZE.getValue() != 1 && UTeam.teams.size() == 1)){
            Main.getInstance().getUHC().setStatut(Statut.FINISH);
            StringBuilder playerst = new StringBuilder();
            int kills = 0;
            for (UPlayer winner : UPlayer.getAlivePlayers()){
                kills = kills + winner.getKills();
                playerst.append(winner.getPlayer().getName()).append(", ");
            }
            String players = playerst.toString().substring(0, playerst.toString().length() - 2);
            Bukkit.broadcastMessage(Message.VICTORY.getMessage().replace("<winners>", players).replace("<kills>", kills + ""));
            if (Main.getInstance().getUHC().getConfiguration().getConfig().getBoolean("twitter.enabled") && Main.getInstance().getUHC().getConfiguration().getConfig().getBoolean("twitter.autovictory.enabled")){
                List<String> post = new ArrayList<>();
                for (String s : Main.getInstance().getUHC().getConfiguration().getConfig().getStringList("twitter.autovictory.post")){
                    String value = s;
                    if (value.contains("<players>")){
                        value.replace("<type>", players);
                    }
                    if (value.contains("<kills>")){
                        value.replace("<hour>", kills + "");
                    }
                    post.add(value);
                }
                StringBuilder tweet = new StringBuilder();
                for (String s : post){
                    tweet.append(s).append("\n");
                }
                Main.getInstance().getUHC().getTwitterManager().tweet(tweet.toString());
            }
                new BukkitRunnable() {
                int i = 0;
                @Override
                public void run() {
                    i++;
                    if (i <= 10) {
                        Firework f = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                        f.detonate();
                        FireworkMeta fM = f.getFireworkMeta();
                        FireworkEffect effect = FireworkEffect.builder()
                                .flicker(true)
                                .withColor(Color.RED)
                                .withFade(Color.BLUE)
                                .withFade(Color.WHITE)
                                .with(FireworkEffect.Type.BURST)
                                .trail(true)
                                .build();

                        fM.setPower(1);
                        fM.addEffect(effect);
                        f.setFireworkMeta(fM);
                    }
                    if (Main.getInstance().getUHC().getConfiguration().getConfig().getBoolean("autorestart.enabled")){
                        if (i == Main.getInstance().getUHC().getConfiguration().getConfig().getInt("autorestart.time")){
                            Bukkit.getServer().shutdown();
                        }
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0, 20);
            /*
            WIN
             */
        }
    }
    public static List<UPlayer> nodamage = new ArrayList<>();
    public void setStart(){
        if (getPlayer() == null)
            return;
        setPlaying(true);
        setCanlatescatter(false);
        ScoreboardLife.setHealth(getPlayer());
        getPlayer().removePotionEffect(PotionEffectType.SLOW);
        getPlayer().removePotionEffect(PotionEffectType.JUMP);
        getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
        getPlayer().setMaxHealth(20);
        getPlayer().setHealth(20);
        getPlayer().setFoodLevel(20);
        getPlayer().setGameMode(GameMode.SURVIVAL);
        getPlayer().getInventory().clear();
        getPlayer().playSound(getPlayer().getLocation(), Sound.ORB_PICKUP, 2f, 2f);
        InventoryUtils.giveStarter(getPlayer());
        for (Scenario scenario : Scenario.getEnabled()){
            scenario.getScenario().scatterPlayer(getPlayer());
        }
        if (RuleInteger.TEAMSIZE.getValue() != 1){
            if (UTeam.getTeamWithPlayer(getPlayer()) == null){
                UTeam team = UTeam.createTeam(this);
                UTeam.addPlayer(getPlayer(), team);
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                getPlayer().playSound(getPlayer().getLocation(), Sound.ORB_PICKUP, 2f, 2f);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        getPlayer().playSound(getPlayer().getLocation(), Sound.ORB_PICKUP, 2f, 2f);
                    }
                }.runTaskLater(Main.getInstance(), 5);
            }
        }.runTaskLater(Main.getInstance(), 5);
    }

    public void destroyScoreboard(){
        board = null;
    }

    public void sendScoreboard(){
        this.board = new FastBoard(getPlayer());
        new BukkitRunnable() {
            @Override
            public void run() {
                if (getPlayer() == null){
                    cancel();
                    return;
                }
                String header = Message.HEADER.getMessage();
                if (header.contains("<online>"))
                    header = header.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                if (header.contains("<slots>"))
                    header = header.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                if (header.contains("<host>"))
                    header = header.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                if (header.contains("<scatter>"))
                    header = header.replace("<scatter>", Scatter.scatter.size() - UPlayer.getAlivePlayers().size() + "");
                if (header.contains("<time>"))
                    header = header.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                if (header.contains("<pvp>"))
                    if (Time.PVP.getTime() > 0)
                        header = header.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                    else header = header.replace("<pvp>", "On");
                if (header.contains("<meetup>"))
                    if (Time.MEETUP.getTime() > 0)
                        header = header.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                    else header = header.replace("<meetup>", "On");
                if (header.contains("<damage>"))
                    if (Time.DAMAGE.getTime() > 0)
                        header = header.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                    else header = header.replace("<damage>", "On");
                if (header.contains("<finalheal>"))
                    if (Time.FINALHEAL.getTime() > 0)
                        header = header.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                    else header = header.replace("<finalheal>", "On");
                if (header.contains("<uhcnumber>"))
                    header = header.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                if (header.contains("<kills>"))
                    header = header.replace("<kills>", getKills() + "");
                if (header.contains("<border>"))
                    header = header.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                String footer = Message.FOOTER.getMessage();
                if (footer.contains("<online>"))
                    footer = footer.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                if (footer.contains("<slots>"))
                    footer = footer.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                if (footer.contains("<host>"))
                    footer = footer.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                if (footer.contains("<scatter>"))
                    footer = footer.replace("<scatter>", Scatter.scatter.size() - UPlayer.getAlivePlayers().size() + "");
                if (footer.contains("<time>"))
                    footer = footer.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                if (footer.contains("<pvp>"))
                    if (Time.PVP.getTime() > 0)
                        footer = footer.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                    else footer = footer.replace("<pvp>", "On");
                if (footer.contains("<meetup>"))
                    if (Time.MEETUP.getTime() > 0)
                        footer = footer.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                    else footer = footer.replace("<meetup>", "On");
                if (footer.contains("<damage>"))
                    if (Time.DAMAGE.getTime() > 0)
                        footer = footer.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                    else footer = footer.replace("<damage>", "On");
                if (footer.contains("<finalheal>"))
                    if (Time.FINALHEAL.getTime() > 0)
                        footer = footer.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                    else footer = footer.replace("<finalheal>", "On");
                if (footer.contains("<uhcnumber>"))
                    footer = footer.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                if (footer.contains("<kills>"))
                    footer = footer.replace("<kills>", getKills() + "");
                if (footer.contains("<border>"))
                    footer = footer.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                Tablist.send(getPlayer(), header, footer);
                String title = ChatColor.translateAlternateColorCodes('&', Message.SCOREBOARDTITLE.getMessage());
                if (title.contains("<online>"))
                    title = title.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                if (title.contains("<slots>"))
                    title = title.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                if (title.contains("<host>"))
                    title = title.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                if (title.contains("<scatter>"))
                    title = title.replace("<scatter>", Scatter.scatter.size() - UPlayer.getAlivePlayers().size() + "");
                if (title.contains("<time>"))
                    title = title.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                if (title.contains("<pvp>"))
                    if (Time.PVP.getTime() > 0)
                        title = title.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                    else title = title.replace("<pvp>", "On");
                if (title.contains("<meetup>"))
                    if (Time.MEETUP.getTime() > 0)
                        title = title.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                    else title = title.replace("<meetup>", "On");
                if (title.contains("<damage>"))
                    if (Time.DAMAGE.getTime() > 0)
                        title = title.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                    else title = title.replace("<damage>", "On");
                if (title.contains("<finalheal>"))
                    if (Time.FINALHEAL.getTime() > 0)
                        title = title.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                    else title = title.replace("<finalheal>", "On");
                if (title.contains("<uhcnumber>"))
                    title = title.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                if (title.contains("<kills>"))
                    title = title.replace("<kills>", getKills() + "");
                if (title.contains("<border>"))
                    title = title.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                board.updateTitle(title);

                List<String> lines = new ArrayList<>();
                if (Main.getInstance().getUHC().getStatut().equals(Statut.WAIT)) {
                    for (String s : RuleList.SCOREBOARDWAIT.getValue()){
                        String line = ChatColor.translateAlternateColorCodes('&', s);
                        if (line.contains("<online>"))
                            line = line.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                        if (line.contains("<slots>"))
                            line = line.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                        if (line.contains("<host>"))
                            line = line.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                        if (line.contains("<scatter>"))
                            line = line.replace("<scatter>", UPlayer.getAlivePlayers().size() - Scatter.scatter.size() + "");
                        if (line.contains("<time>"))
                            line = line.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                        if (line.contains("<pvp>"))
                            if (Time.PVP.getTime() > 0)
                                line = line.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                            else line = line.replace("<pvp>", "On");
                        if (line.contains("<meetup>"))
                            if (Time.MEETUP.getTime() > 0)
                                line = line.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                            else line = line.replace("<meetup>", "On");
                        if (line.contains("<damage>"))
                            if (Time.DAMAGE.getTime() > 0)
                                line = line.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                            else line = line.replace("<damage>", "On");
                        if (line.contains("<finalheal>"))
                            if (Time.FINALHEAL.getTime() > 0)
                                line = line.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                            else line = line.replace("<finalheal>", "On");
                        if (line.contains("<uhcnumber>"))
                            line = line.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                        if (line.contains("<border>"))
                            line = line.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                        if (line.contains("<kills>"))
                            line = line.replace("<kills>", getKills() + "");
                        lines.add(line);
                    }
                }else
                if (Main.getInstance().getUHC().getStatut().equals(Statut.SCATTER)) {
                    for (String s : RuleList.SCOREBOARDSCATTER.getValue()){
                        String line = ChatColor.translateAlternateColorCodes('&', s);
                        if (line.contains("<online>"))
                            line = line.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                        if (line.contains("<slots>"))
                            line = line.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                        if (line.contains("<host>"))
                            line = line.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                        if (line.contains("<scatter>"))
                            line = line.replace("<scatter>", UPlayer.getAlivePlayers().size() - Scatter.scatter.size() + "");
                        if (line.contains("<time>"))
                            line = line.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                        if (line.contains("<pvp>"))
                            if (Time.PVP.getTime() > 0)
                                line = line.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                            else line = line.replace("<pvp>", "On");
                        if (line.contains("<meetup>"))
                            if (Time.MEETUP.getTime() > 0)
                                line = line.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                            else line = line.replace("<meetup>", "On");
                        if (line.contains("<damage>"))
                            if (Time.DAMAGE.getTime() > 0)
                                line = line.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                            else line = line.replace("<damage>", "On");
                        if (line.contains("<finalheal>"))
                            if (Time.FINALHEAL.getTime() > 0)
                                line = line.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                            else line = line.replace("<finalheal>", "On");
                        if (line.contains("<uhcnumber>"))
                            line = line.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                        if (line.contains("<border>"))
                            line = line.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                        if (line.contains("<kills>"))
                            line = line.replace("<kills>", getKills() + "");
                        lines.add(line);
                    }
                }else {
                    for (String s : RuleList.SCOREBOARDGAME.getValue()){
                        String line = ChatColor.translateAlternateColorCodes('&', s);
                        if (line.contains("<online>"))
                            line = line.replace("<online>", UPlayer.getAlivePlayers().size() + "");
                        if (line.contains("<slots>"))
                            line = line.replace("<slots>", RuleInteger.SLOTS.getValue() + "");
                        if (line.contains("<host>"))
                            line = line.replace("<host>", UPlayer.getHost == null ? "None" : UPlayer.getHost.getOfflinePlayer().getName());
                        if (line.contains("<scatter>"))
                            line = line.replace("<scatter>", UPlayer.getAlivePlayers().size() - Scatter.scatter.size() + "");
                        if (line.contains("<time>"))
                            line = line.replace("<time>", Time.TIME.getMinute() + ":" + Time.TIME.getSeconde());
                        if (line.contains("<pvp>"))
                            if (Time.PVP.getTime() > 0)
                            line = line.replace("<pvp>", Time.PVP.getMinute() + ":" + Time.PVP.getSeconde());
                            else line = line.replace("<pvp>", "On");
                        if (line.contains("<meetup>"))
                            if (Time.MEETUP.getTime() > 0)
                            line = line.replace("<meetup>", Time.MEETUP.getMinute() + ":" + Time.MEETUP.getSeconde());
                            else line = line.replace("<meetup>", "On");
                        if (line.contains("<damage>"))
                            if (Time.DAMAGE.getTime() > 0)
                                line = line.replace("<damage>", Time.DAMAGE.getMinute() + ":" + Time.DAMAGE.getSeconde());
                            else line = line.replace("<damage>", "On");
                        if (line.contains("<finalheal>"))
                            if (Time.FINALHEAL.getTime() > 0)
                                line = line.replace("<finalheal>", Time.FINALHEAL.getMinute() + ":" + Time.FINALHEAL.getSeconde());
                            else line = line.replace("<finalheal>", "On");
                        if (line.contains("<uhcnumber>"))
                            line = line.replace("<uhcnumber>", RuleInteger.UHCNUMBER.getValue() + "");
                        if (line.contains("<border>"))
                            line = line.replace("<border>", ((int)Bukkit.getWorld("world").getWorldBorder().getSize()) + "");
                        if (line.contains("<kills>"))
                            line = line.replace("<kills>", getKills() + "");
                        lines.add(line);
                    }
                }
                board.updateLine(lines);
                /*Map<Integer, String> lines = new HashMap<>();
                lines.put(UPlayer.getAlivePlayers().size(), "Online");
                for (UPlayer player : UPlayer.getOnlinePlayers()){
                    lines.put(player.getKills(), player.getPlayer().getName());
                }
                board.updateLine(lines);*/


            }
        }.runTaskTimerAsynchronously(Main.getInstance(), 0, 20);
    }


    public static UPlayer getUPlayer(UUID uuid){
        for (UPlayer pl : getPlayers){
            if (pl.getUniqueId().toString().equalsIgnoreCase(uuid.toString()))
                return pl;
        }
        return new UPlayer(uuid.toString());
    }

    public static List<UPlayer> getPlayers = new ArrayList<>();

    public static List<UPlayer> getAlivePlayers(){
        List<UPlayer> value = new ArrayList<>();
        for (UPlayer ups : getPlayers){
            if (ups.getPlayer() != null && ups.isPlaying()){
                value.add(ups);
            }
        }
        return value;
    }

    public static List<UPlayer> getSpecPlayers(){
        List<UPlayer> value = new ArrayList<>();
        for (UPlayer ups : getPlayers){
            if (ups.getPlayer() != null && !ups.isPlaying()){
                value.add(ups);
            }
        }
        return value;
    }

    public static List<UPlayer> getOnlinePlayers(){
        List<UPlayer> value = new ArrayList<>();
        for (UPlayer ups : getPlayers){
            if (ups.getPlayer() != null){
                value.add(ups);
            }
        }
        return value;
    }

    public static UPlayer getHost = null;
}
