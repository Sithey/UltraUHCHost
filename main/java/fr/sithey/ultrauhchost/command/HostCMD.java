package fr.sithey.ultrauhchost.command;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.player.Scenarios;
import fr.sithey.ultrauhchost.lib.InventoryUtils;
import fr.sithey.ultrauhchost.lib.ScoreboardLife;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HostCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ultrauhc.host")) {
                if (cmd.getName().equalsIgnoreCase("bypass")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("finalheal")) {
                            Time.FINALHEAL.setTime(Time.TIME.getTime() + 3);
                        } else if (args[0].equalsIgnoreCase("pvp")) {
                            Time.PVP.setTime(Time.TIME.getTime() + 3);
                        } else if (args[0].equalsIgnoreCase("meetup")) {
                            Time.MEETUP.setTime(Time.TIME.getTime() + 3);
                        } else player.sendMessage("/bypass <finalheal/pvp/meetup>");
                    } else {
                        player.sendMessage(Message.PREFIX.getMessage() +"/bypass <finalheal/pvp/meetup>");
                    }
                }
                if (cmd.getName().equalsIgnoreCase("heal")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("all")) {
                            for (Player all : Bukkit.getOnlinePlayers()){
                                all.setHealth(all.getMaxHealth());
                                ScoreboardLife.setHealth(player);
                            }
                        } else {
                            Player cible = Bukkit.getPlayer(args[0]);
                            cible.setHealth(cible.getMaxHealth());
                            ScoreboardLife.setHealth(player);
                        }
                    }
                }

                if (cmd.getName().equalsIgnoreCase("twitter") && Main.getInstance().getUHC().getConfiguration().getConfig().getBoolean("twitter.enabled")) {
                    if (Main.getInstance().isPremium()) {
                        if (args.length == 1) {
                            String hour = args[0];
                            List<String> post = new ArrayList<>();
                            for (String s : Main.getInstance().getUHC().getConfiguration().getConfig().getStringList("twitter.post")) {
                                String value = s;
                                if (value.contains("<type>")) {
                                    value = value.replace("<type>", RuleInteger.TEAMSIZE.getValue() == 1 ? "FFA" : "To" + RuleInteger.TEAMSIZE.getValue());
                                }
                                if (value.contains("<hour>")) {
                                    value = value.replace("<hour>", hour);
                                }
                                if (value.contains("<scenarios>")) {
                                    StringBuilder scenarios = new StringBuilder();
                                    for (Scenario sc : Scenario.getEnabled()) {
                                        scenarios.append(" ").append(sc.getScenario().getName());
                                    }
                                    value = value.replace("<scenarios>", scenarios);
                                }
                                post.add(value);
                            }

                            StringBuilder tweet = new StringBuilder();
                            for (String s : post) {
                                tweet.append(s).append("\n");
                            }
                            Main.getInstance().getUHC().getTwitterManager().tweet(tweet.toString());
                        } else {
                            player.sendMessage(Message.PREFIX.getMessage() + "/twitter <hour>");
                        }
                    }else{
                        Main.getInstance().sendPremiumMessage(player);
                    }
                }

                if (cmd.getName().equalsIgnoreCase("saveitems") && Main.getInstance().getUHC().getStatut().isLobby()) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("starter")){
                            InventoryUtils.saveStarter(player);
                            UPlayer.getUPlayer(player.getUniqueId()).setLobby();
                        }
                        if (args[0].equalsIgnoreCase("death")){
                            InventoryUtils.saveDeath(player);
                            UPlayer.getUPlayer(player.getUniqueId()).setLobby();
                        }
                    }
                }

                if (cmd.getName().equalsIgnoreCase("finishuhc")) {
                    Main.getInstance().getUHC().deleteWorld(new File("world"));
                    Main.getInstance().getUHC().deleteWorld(new File("world_nether"));
                    Bukkit.getServer().shutdown();
                }

                if (cmd.getName().equalsIgnoreCase("sethost")) {
                    if (UPlayer.getHost != null){
                        if (UPlayer.getPlayers != null){
                            if (Main.getInstance().getUHC().getStatut().isLobby()) {
                                UPlayer.getHost.setPlaying(true);
                            }else{
                                UPlayer.getHost.setPlaying(false);
                                UPlayer.getHost.getPlayer().setGameMode(GameMode.SPECTATOR);
                            }
                        }
                    }
                    UPlayer.getHost = UPlayer.getUPlayer(player.getUniqueId());
                    UPlayer.getHost.setPlaying(false);
                }

                if (cmd.getName().equalsIgnoreCase("mutechat")) {
                    RuleBoolean.MUTECHAT.setEnabled(!RuleBoolean.MUTECHAT.isEnabled());
                    /*
                    MESSAGE DE MUTE
                     */
                }

                if (cmd.getName().equalsIgnoreCase("setnumber")) {
                    if (args.length == 1) {
                        RuleInteger.UHCNUMBER.setValue(Integer.parseInt(args[0]));
                    }
                }

               /* if (cmd.getName().equalsIgnoreCase("revive")) {
                    if (args.length == 1) {
                        UPlayer account = Main.getInstance().uhc.getPlayer(Bukkit.getPlayer(args[0]).getUniqueId());
                        account.revive();
                    }
                }*/

                if (cmd.getName().equalsIgnoreCase("tpto")) {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            return false;
                        }

                        player.teleport(target.getLocation());
                    }
                }

                if (cmd.getName().equalsIgnoreCase("rtp")) {
                    if (UPlayer.getAlivePlayers().isEmpty())
                        return false;
                    UPlayer choix = UPlayer.getAlivePlayers().get(new Random().nextInt(UPlayer.getAlivePlayers().size()));
                    if (choix.getPlayer() == null) {
                        player.performCommand("rtp");
                        return false;
                    }
                    /*
                    MSG TP
                     */
                    player.teleport(choix.getPlayer().getLocation());
                }

                if (cmd.getName().equalsIgnoreCase("moveteam")) {
                    if (args.length == 2) {
                        Player cible = Bukkit.getPlayer(args[0]);
                        Player tocible = Bukkit.getPlayer(args[1]);
                        if (cible == null || tocible == null)
                            return false;
                        UTeam team = UTeam.getTeamWithPlayer(tocible);
                        if (team == null)
                            return false;
                        UTeam.addPlayerBYPASS(cible, team);
                        cible.teleport(tocible);
                    }else{
                        player.sendMessage(Message.PREFIX.getMessage() +"/moveteam <player> <nexteamplayer>");
                    }
                }

                if (cmd.getName().equalsIgnoreCase("spec")) {
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("list")) {
                            StringBuilder list = new StringBuilder();
                            int i = 1;
                            for (UPlayer spec : UPlayer.getSpecPlayers()) {
                                if (list.length() > 0) {
                                    if (i == UPlayer.getSpecPlayers().size()) {
                                        list.append(" and ");
                                    } else {
                                        list.append(", ");
                                    }
                                }
                                list.append(Bukkit.getOfflinePlayer(spec.getUniqueId()).getName());
                                i++;
                            }
                            sender.sendMessage(Message.PREFIX.getMessage() + "There are " + (i - 1) + " spectators:");
                            sender.sendMessage(list.toString());
                        }
                    } else if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("add")) {
                            UPlayer uPlayer = UPlayer.getUPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            uPlayer.setPlaying(false);
                            if (uPlayer.getPlayer() != null)
                                uPlayer.getPlayer().setGameMode(GameMode.SPECTATOR);
                        }
                        if (args[0].equalsIgnoreCase("remove")) {
                            UPlayer uPlayer = UPlayer.getUPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            uPlayer.setPlaying(true);
                            if (uPlayer.getPlayer() != null)
                                uPlayer.getPlayer().setGameMode(GameMode.ADVENTURE);
                        }

                    }

             /*   if (cmd.getName().equalsIgnoreCase("vote")) {
                    if (args.length > 0) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                            players.sendMessage(Main.getInstance().uhc.prefix + "You have 20 seconds to answer the vote :§c " + message);
                            new JsonMessage().append("§2Yes").setClickAsExecuteCmd("addOuivote31").save().send(players);
                            new JsonMessage().append("§4No").setClickAsExecuteCmd("addNonvote31").save().send(players);
                        }
                        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                Bukkit.broadcastMessage(Main.getInstance().uhc.prefix + "§bResult : §2Yes : " + yes + " §cNo : " + no);
                                vote.clear();
                                yes = 0;
                                no = 0;
                            }
                        }, 20 * 20);
                    }
                }*/

              /*  } else if (args[0].equalsIgnoreCase("say")) {
                    Player p = (Player) sender;
                    String message = "";
                    for (int i = 1; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }
                    Bukkit.broadcastMessage(Main.getInstance().uhc.prefix + "§3(§bHost§3) §e" + p.getDisplayName() + " §8» §c" + message);
                } else if (args[0].equalsIgnoreCase("revive")) {

                } else if (args[0].equalsIgnoreCase("tp")) {

                } else if (args[0].equalsIgnoreCase("vote")) {

                } else if (args[0].equalsIgnoreCase("players")) {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("list")) {
                            StringBuilder list = new StringBuilder();
                            int i = 1;
                            for (UPlayer spec : Main.getInstance().uhc.getAllAlive()) {
                                if (list.length() > 0) {
                                    if (i == Main.getInstance().uhc.getAllAlive().size()) {
                                        list.append(" and ");
                                    } else {
                                        list.append(", ");
                                    }
                                }
                                list.append(spec.getName());
                                i++;
                            }
                            sender.sendMessage(Main.getInstance().uhc.prefix + "§7Players: §b" + (i - 1));
                            sender.sendMessage(Main.getInstance().uhc.prefix + "§7Pseudo: §7§o" + list.toString() + ".");
                        }
                    } else if (args.length == 3) {
                        if (args[1].equalsIgnoreCase("remove")) {
                            UPlayer uPlayer = Main.getInstance().uhc.getPlayer(args[2]);
                            uPlayer.setPlaying(false);
                            player.sendMessage("§c✖ §b" + args[2]);
                            Main.getInstance().uhc.checkwin();
                        }
                    } else {
                        sendhelp(player);
                    }
                }
                else if (args[0].equalsIgnoreCase("teams")) {
                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("list")) {
                            StringBuilder list = new StringBuilder();
                            int i = 1;
                            for (UTeam spec : Main.getInstance().uhc.getTeamsAlive()) {
                                if (list.length() > 0) {
                                    if (i == Main.getInstance().uhc.getTeamsAlive().size()) {
                                        list.append(" and ");
                                    } else {
                                        list.append(", ");
                                    }
                                }
                                String s = spec.getPrefix() + spec.getOwner().getName();
                                list.append(s);
                                i++;
                            }
                            sender.sendMessage(Main.getInstance().uhc.prefix + "§7Teams: §b" + (i - 1));
                            sender.sendMessage(Main.getInstance().uhc.prefix + "§7Name: §7§o" + list.toString() + ".");
                        }
                    } else if (args.length == 3) {
                        if (args[1].equalsIgnoreCase("remove")) {
                            UTeam uPlayer = UTeam.getTeamWithOwner(args[2]);
                            UTeam.removeTeam(uPlayer);
                            player.sendMessage("§c✖ §b" + args[2]);
                            Main.getInstance().uhc.checkwin();
                        }
                    } else {
                        sendhelp(player);
                    }
                }
            }
        }
        return false;*/
                }
                if (cmd.getName().equalsIgnoreCase("revive")) {
                    if (args.length == 1){
                        Player cible = Bukkit.getPlayer(args[0]);
                        if (cible == null)
                            return false;
                        UPlayer c = UPlayer.getUPlayer(cible.getUniqueId());
                        if (c.isPlaying())
                            return false;
                        if (c.getDeath() == null)
                            return false;
                        c.respawn();
                    }
                }
            }
        }
            return false;
    }


}
