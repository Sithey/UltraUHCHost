package fr.sithey.ultrauhchost.management.object;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.Prefix;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class UTeam {

    private Prefix name;
    private UPlayer owner;
    private ArrayList<UUID> invitation;
    private List<UPlayer> member, alive;

    public UTeam(UPlayer owner, Prefix name){
        this.owner = owner;
        this.name = name;
        if (name == null) {
            this.name = Prefix.getRandom();
        }else{
            this.name = name;
        }

        this.member = new ArrayList<>();
        this.invitation = new ArrayList<>();
        setupTeam();
        teams.add(this);
    }

    public UTeam(UPlayer owner) {
        this(owner, null);
    }
    public UTeam(Prefix name) {
        this(null, name);
    }

    public String getPrefix(){
        return name.color + name.symbol;
    }

    public Prefix getName() {
        return name;
    }

    public UPlayer getOwner() {
        return owner;
    }

    public List<UPlayer> getMember() {
        return member;
    }

    public List<UPlayer> getAlive() {
        List<UPlayer> value = new ArrayList<>();
        for (UPlayer membre : member){
            if (membre.isPlaying() && membre.getPlayer() != null){
                value.add(membre);
            }
        }
        return value;
    }

    public ArrayList<UUID> getInvitation() {
        return invitation;
    }

    private void setupTeam() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        if (scoreboard.getTeam(this.getPrefix()) == null) {
            Team t = scoreboard.registerNewTeam(this.getPrefix());
            t.setPrefix(this.getPrefix());
            t.setAllowFriendlyFire(RuleBoolean.FRIENDLYFIRE.isEnabled());
            t.setSuffix(String.valueOf(ChatColor.RESET));
        }
    }
    
    public static List<UTeam> teams = new ArrayList<>();
    
    public static void addPlayerBYPASS(Player player, UTeam team){
        UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());
        if (team.getAlive().contains(pl))
            return;

        for (UTeam teams :teams){
            if (teams.getAlive().contains(pl)){
                removePlayerNoMSG(player, teams);
            }
        }


            team.getMember().add(pl);
            team.getAlive().add(pl);
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getPrefix()).addEntry(player.getName());
    }
    public static void addPlayer(Player player, UTeam team){
        UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());
        if (team.getAlive().contains(pl))
            return;

        for (UTeam teams : teams){
            if (teams.getAlive().contains(pl)){
                removePlayer(player, teams);
            }
        }

        if (team.getMember().size() < RuleInteger.TEAMSIZE.getValue()) {
            team.getMember().add(pl);
            team.getAlive().add(pl);
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getPrefix()).addEntry(player.getName());
            /*
            ADD DANS TEAM
            for (UPlayer inteam : team.getAlive())
                Bukkit.getPlayer(inteam.getUniqueId()).sendMessage(Main.getInstance().uhc.prefix + Files.MESSAGE.getConfig().getString(MessageFile.TEAMJOIN.getPath()).replace("&", "§").replace("<player>", player.getName()).replace("<team>", String.valueOf(team.getPrefix()+ Bukkit.getOfflinePlayer(team.getOwner().getUniqueId()).getName())));
            */

        } else {

            /*
            TEAM FULL
             */
        }

    }

    public static void removePlayer(Player player, UTeam team){
        UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());
        if (team.getMember().contains(pl)){
            team.getMember().remove(pl);
            team.getAlive().remove(pl);
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getPrefix()).removeEntry(player.getName());
            /*
            ADD DANS TEAM
                        for (UPlayer inteam : team.getAlive())
                Bukkit.getPlayer(inteam.getUniqueId()).sendMessage(Main.getInstance().uhc.prefix + Files.MESSAGE.getConfig().getString(MessageFile.TEAMLEAVE.getPath()).replace("&", "§").replace("<player>", player.getName()).replace("<team>", String.valueOf(team.getPrefix()+ Bukkit.getOfflinePlayer(team.getOwner().getUniqueId()).getName())));

             */
  }

        if (team.getMember().size() == 0){
            removeTeam(team);
        }
    }
    public static void removePlayerNoMSG(Player player, UTeam team){
        UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());
        if (team.getMember().contains(pl)){
            team.getMember().remove(pl);
            team.getAlive().remove(pl);
            Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getPrefix()).removeEntry(player.getName());
        }
    }
    public static UTeam getTeamWithPlayer(Player player) {
        for (UTeam teams : teams) {
            if (!teams.getMember().contains(UPlayer.getUPlayer(player.getUniqueId()))) continue;
            return teams;
        }
        return null;
    }

    public static UTeam getTeamWithName(String name) {
        for (UTeam teams : teams) {
            if (!teams.getPrefix().equals(name)) continue;
            return teams;
        }
        return null;
    }

    public static void reset(){
        for (Player player : Bukkit.getOnlinePlayers()){
            if (UTeam.getTeamWithPlayer(player) != null)
                removePlayer(player, UTeam.getTeamWithPlayer(player));
        }
        for (UTeam team : teams)
            removeTeam(team);
        for (Team t : Main.getInstance().getServer().getScoreboardManager().getMainScoreboard().getTeams())
            t.unregister();

    }

    public static UTeam createTeam(UPlayer owner) {
        UTeam UHCTeam = new UTeam(owner);
        UTeam.addPlayerBYPASS(Bukkit.getPlayer(owner.getUniqueId()), UHCTeam);
        return UHCTeam;
    }

    public static UTeam createTeam(Prefix owner) {
        UTeam UHCTeam = new UTeam(owner);
        return UHCTeam;
    }


    public static void removeTeam(UTeam team){
        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getPrefix()).unregister();
        teams.remove(team);
    }

    public static void sendInvitation(UPlayer cible, UTeam team) {
        Player player = Bukkit.getPlayer(cible.getUniqueId());
        UPlayer owner = team.owner;
        /*
        MSG DINVITE DE TEAM
         */
       // player.sendMessage(MessageFile.TEAMINVITE.getValue().replace("<owner>", Bukkit.getOfflinePlayer(owner.getUniqueId()).getName()).replace("<team>", String.valueOf(team.getPrefix() + Bukkit.getOfflinePlayer(team.getOwner().getUniqueId()).getName())));
        player.spigot().sendMessage(new ComponentBuilder("§b/team accept " + Bukkit.getOfflinePlayer(owner.getUniqueId()).getName() ).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/team accept " +  Bukkit.getOfflinePlayer(owner.getUniqueId()).getName())).create());
        team.getInvitation().add(cible.getUniqueId());
    }

    public static void randomTeams() {
        int max = RuleInteger.TEAMSIZE.getValue();
        List<UPlayer> players = new ArrayList<>();
        for (UPlayer up : UPlayer.getAlivePlayers())
            if (getTeamWithPlayer(Bukkit.getPlayer(up.getUniqueId())) == null)
            players.add(up);

        int nbTeams = players.size() / max;
        if ((players.size() % max) != 0)
            nbTeams++;
        for (int i = 0; i <= nbTeams; i++)
            attributeToTeam(players, max);
    }

    private static void attributeToTeam(List<UPlayer> players, int teamSize) {
        Collections.shuffle(players);
        UTeam team = createTeam(Prefix.getRandom());
        for (int i = 0; i < teamSize; i++) {
            if (players.isEmpty())
                break;
            UPlayer player = players.get(0);
            if (player != null)
            addPlayer(Bukkit.getPlayer(player.getUniqueId()), team);
            players.remove(player);
        }
    }

}
