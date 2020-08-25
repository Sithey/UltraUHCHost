package fr.sithey.ultrauhchost.command;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import fr.sithey.ultrauhchost.management.scenarios.TeamInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.sithey.ultrauhchost.management.enumeration.Statut.WAIT;

public class TeamCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && RuleInteger.TEAMSIZE.getValue() != 1){
            Player player = (Player) sender;
            UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());

            if (args.length == 0) {
                sendHelp(player);
            }
            if (Main.getInstance().getUHC().getStatut().equals(WAIT)) {
                if (args.length == 1) {

                    if (UTeam.getTeamWithPlayer(player) != null) {
                        if (args[0].equalsIgnoreCase("list")) {
                            StringBuilder list = new StringBuilder();
                            int i = 1;
                            for (UPlayer spec : UTeam.getTeamWithPlayer(player).getMember()) {
                                if (list.length() > 0) {
                                    if (i == UTeam.getTeamWithPlayer(player).getMember().size()) {
                                        list.append(" and ");
                                    } else {
                                        list.append(", ");
                                    }
                                }
                                list.append(Bukkit.getOfflinePlayer(spec.getUniqueId()).getName());
                                i++;
                            }
                            player.sendMessage(Message.PREFIX.getMessage() + "§7Team " + UTeam.getTeamWithPlayer(player).getPrefix() + Bukkit.getOfflinePlayer(UTeam.getTeamWithPlayer(player).getOwner().getUniqueId()).getName() + "§7 members : §b" + list.toString() + "§7.");

                        }
                        if (UTeam.getTeamWithPlayer(player).getOwner().equals(pl) && args[0].equalsIgnoreCase("delete")) {
                            UTeam.removeTeam(UTeam.getTeamWithPlayer(player));
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("create")) {
                            if (RuleBoolean.RANDOMTEAM.isEnabled())
                                return false;
                            UTeam team = UTeam.createTeam(pl);
                            UTeam.addPlayer(player, team);
                        }
                    }
                } else if (args.length == 2) {
                    if (UTeam.getTeamWithPlayer(player) == null) {
                        if (args[0].equalsIgnoreCase("accept")) {
                            UPlayer owner = UPlayer.getUPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            if (UTeam.getTeamWithPlayer(Bukkit.getPlayer(owner.getUniqueId())) != null && UTeam.getTeamWithPlayer(Bukkit.getPlayer(owner.getUniqueId())).getInvitation().contains(pl.getUniqueId())) {
                                UTeam.addPlayer(player, UTeam.getTeamWithPlayer(Bukkit.getPlayer(owner.getUniqueId())));
                            }
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("kick") && UTeam.getTeamWithPlayer(player).getOwner().equals(pl)) {
                            UPlayer cible = UPlayer.getUPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            if (UTeam.getTeamWithPlayer(player).getMember().contains(cible)) {
                                UTeam.removePlayer(Bukkit.getPlayer(cible.getUniqueId()), UTeam.getTeamWithPlayer(player));
                            }
                        }
                        if (args[0].equalsIgnoreCase("invite") && UTeam.getTeamWithPlayer(player).getOwner().equals(pl)) {
                            if (Bukkit.getPlayer(args[1]) == null){
                                return false;
                            }
                            UPlayer cible = UPlayer.getUPlayer(Bukkit.getPlayer(args[1]).getUniqueId());
                            if (UTeam.getTeamWithPlayer(Bukkit.getPlayer(cible.getUniqueId())) == null) {
                                UTeam.sendInvitation(cible, UTeam.getTeamWithPlayer(player));
                            } else {
                                sendHelp(player);
                            }
                        }
                    }
                }
            }else{
                if (args[0].equalsIgnoreCase("coord") && args.length == 1) {
                    for (UPlayer p : UTeam.getTeamWithPlayer(player).getAlive()) {
                        Bukkit.getPlayer(p.getUniqueId()).sendMessage(Message.PREFIX.getMessage() + "Coordinate of §e" + player.getName() + " : §b" + ((int) Bukkit.getPlayer(p.getUniqueId()).getLocation().getX()) + "," + ((int) Bukkit.getPlayer(p.getUniqueId()).getLocation().getY()) + "," + ((int) Bukkit.getPlayer(p.getUniqueId()).getLocation().getZ()));
                    }
                }
                if (Scenario.TEAMINVENTORY.isEnabled() && args[0].equalsIgnoreCase("inventory") && args.length == 1) {
                    if(pl.isPlaying() && UTeam.getTeamWithPlayer(player) != null) {
                        player.openInventory(TeamInventory.inventory.get(UTeam.getTeamWithPlayer(player)));
                    }
                }
                if (Scenario.HEALTHDONOR.isEnabled() && args[0].equalsIgnoreCase("donate") && args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) == null)
                        return false;
                    if (UTeam.getTeamWithPlayer(Bukkit.getPlayer(args[1])) == null || UTeam.getTeamWithPlayer(Bukkit.getPlayer(args[1])) != UTeam.getTeamWithPlayer(player))
                        return false;
                    int health = Integer.parseInt(args[2]);
                    if (health > player.getHealth()){
                        if (health + Bukkit.getPlayer(args[1]).getHealth() <= Bukkit.getPlayer(args[1]).getMaxHealth())
                        Bukkit.getPlayer(args[1]).setHealth(Bukkit.getPlayer(args[1]).getHealth() + health);
                        else Bukkit.getPlayer(args[1]).setHealth(Bukkit.getPlayer(args[1]).getMaxHealth());
                        player.setHealth(player.getHealth() - health);
                    }
                }
            }
        }
        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage(Message.PREFIX.getMessage() + "Team commands");
        if (UTeam.getTeamWithPlayer(player) == null){
            player.sendMessage(ChatColor.RED + "/team create");
        return;
        }
        player.sendMessage(ChatColor.RED +"/team coord");
        player.sendMessage(ChatColor.RED +"/team list");
        if (Scenario.HEALTHDONOR.isEnabled()){
            player.sendMessage(ChatColor.RED +"/team donate <player> <health>");
        }
        if (Scenario.TEAMINVENTORY.isEnabled()){
            player.sendMessage(ChatColor.RED +"/team inventory");
        }
        if (UTeam.getTeamWithPlayer(player) != null && UTeam.getTeamWithPlayer(player).getOwner().equals(UPlayer.getUPlayer(player.getUniqueId()))) {
            player.sendMessage(ChatColor.RED +"/team delete");
            player.sendMessage(ChatColor.RED +"/team invite <player>");
            player.sendMessage(ChatColor.RED +"/team kick <player>");
        }
    }

}
