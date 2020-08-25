package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class King extends CustomScenario {
    @Override
    public String getName() {
        return "King";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.KING.getDescription().split(" ")){
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
        return new ItemCreator(Material.GOLD_SWORD).setName(getName() + " : " + Scenario.KING.isEnabled()).setAmount(Scenario.KING.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    List<UPlayer> kings = new ArrayList<>();
    @Override
    public void onStart() {
        for (UTeam team : UTeam.teams){
            UPlayer king = team.getAlive().get(new Random().nextInt(team.getAlive().size()));
            kings.add(king);
            king.getPlayer().sendMessage(Message.PREFIX.getMessage() + "You are the king");
            king.getPlayer().setMaxHealth(40);
            king.getPlayer().setHealth(40);
            king.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 0, false, false));
            king.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 0, false, false));
            king.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, 0, false, false));
        }
    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {

    }

    @Override
    public void onDeath(Player player, Player killer) {
        if (kings.contains(UPlayer.getUPlayer(player.getUniqueId()))){
            if (!UTeam.getTeamWithPlayer(player).getAlive().isEmpty()){
                for (UPlayer other : UTeam.getTeamWithPlayer(player).getAlive()){
                    other.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 2 * 20 * 60, 0, false, false));
                }
            }
        }
    }

    @Override
    public String getPath() {
        return "king";
    }


}
