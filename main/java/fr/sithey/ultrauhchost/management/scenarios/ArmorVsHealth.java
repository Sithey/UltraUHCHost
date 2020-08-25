package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArmorVsHealth extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Armor vs Health";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.ARMORVSHEALTH.getDescription().split(" ")){
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
        return new ItemCreator(Material.DIAMOND_CHESTPLATE).setName(getName() + " : " + Scenario.ARMORVSHEALTH.isEnabled()).setAmount(Scenario.ARMORVSHEALTH.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
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

    }

    @Override
    public String getPath() {
        return "armorvshealth";
    }

    @EventHandler
    public void GodApple(CraftItemEvent event) {
            Player p = (Player) event.getView().getPlayer();

            org.bukkit.Material itemType = event.getInventory().getResult().getType();
            if (itemType == null) {
                return;
            }
            switch (itemType) {
                default:break;
                case LEATHER_BOOTS:
                    damage(p, 1.0D);
                    break;
                case LEATHER_LEGGINGS:
                    damage(p, 3.0D);
                    break;
                case LEATHER_CHESTPLATE:
                    damage(p, 2.0D);
                    break;
                case LEATHER_HELMET:
                    damage(p, 1.0D);
                    break;
                case GOLD_BOOTS:
                    damage(p, 2.0D);
                    break;
                case GOLD_LEGGINGS:
                    damage(p, 5.0D);
                    break;
                case GOLD_CHESTPLATE:
                    damage(p, 3.0D);
                    break;
                case GOLD_HELMET:
                    damage(p, 1.0D);
                    break;
                case IRON_BOOTS:
                    damage(p, 2.0D);
                    break;
                case IRON_LEGGINGS:
                    damage(p, 3.0D);
                    break;
                case IRON_CHESTPLATE:
                    damage(p, 5.0D);
                    break;
                case IRON_HELMET:
                    damage(p, 2.0D);
                    break;
                case DIAMOND_BOOTS:
                    damage(p, 3.0D);
                    break;
                case DIAMOND_LEGGINGS:
                    damage(p, 8.0D);
                    break;
                case DIAMOND_CHESTPLATE:
                    damage(p, 6.0D);
                    break;
                case DIAMOND_HELMET:
                    damage(p, 3.0D);
                    break;

        }
    }


    private void damage(Player player, double amount) {
        EntityDamageEvent event = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.CUSTOM, amount);
        Bukkit.getPluginManager().callEvent(event);
        player.damage(event.getDamage());
    }


}
