package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoldenFleece extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Golden Fleece";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.GOLDENFLEECE.getDescription().split(" ")){
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
        return new ItemCreator(Material.WOOL).setName(getName() + " : " + Scenario.GOLDENFLEECE.isEnabled()).setAmount(Scenario.GOLDENFLEECE.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @EventHandler
    public void onSheepDeath(EntityDeathEvent event){
           if (event.getEntity().getType().equals(EntityType.SHEEP)){
               int random = new Random().nextInt(100);
               if (random <= 10){
                   Entity skeleton = event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.SKELETON);
                   Skeleton equip = (Skeleton) skeleton;
                   equip.getEquipment().setHelmet(new ItemCreator(Material.GOLD_HELMET).getItem());
                   equip.getEquipment().setHelmet(new ItemCreator(Material.GOLD_CHESTPLATE).getItem());
                   equip.getEquipment().setHelmet(new ItemCreator(Material.GOLD_LEGGINGS).getItem());
                   equip.getEquipment().setHelmet(new ItemCreator(Material.GOLD_BOOTS).getItem());
               }else if (random <= 20){
                   event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemCreator(Material.DIAMOND).getItem());
               }else if (random <= 50){
                   event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemCreator(Material.GOLD_INGOT).getItem());
               }else{
                   event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), new ItemCreator(Material.IRON_INGOT).getItem());
               }
           }
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
        return "goldenfleece";
    }
}
