package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CarrotCombo extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Carrot Combo";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.CARROTCOMBO.getDescription().split(" ")){
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
        return new ItemCreator(Material.GOLDEN_CARROT).setName(getName() + " : " + Scenario.CARROTCOMBO.isEnabled()).setAmount(Scenario.CARROTCOMBO.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "carrotcombo";
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent e) {
            Material itemType = e.getInventory().getResult().getType();
            switch (itemType) {
                case WOOD_SWORD:
                case GOLD_SWORD: {
                    ItemStack lo1 = new ItemStack(Material.CARROT_ITEM);
                    ItemMeta lo1M = lo1.getItemMeta();
                    lo1M.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                    lo1.setItemMeta(lo1M);
                    e.getInventory().setResult(lo1);
                    break;
                }
                case STONE_SWORD: {
                    ItemStack lo1 = new ItemStack(Material.CARROT_ITEM);
                    ItemMeta lo1M = lo1.getItemMeta();
                    lo1M.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                    lo1.setItemMeta(lo1M);
                    e.getInventory().setResult(lo1);
                    break;
                }
                case IRON_SWORD: {
                    ItemStack lo1 = new ItemStack(Material.CARROT_ITEM);
                    ItemMeta lo1M = lo1.getItemMeta();
                    lo1M.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
                    lo1.setItemMeta(lo1M);
                    e.getInventory().setResult(lo1);
                    break;
                }
                case DIAMOND_SWORD: {
                    ItemStack lo1 = new ItemStack(Material.CARROT_ITEM);
                    ItemMeta lo1M = lo1.getItemMeta();
                    lo1M.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
                    lo1.setItemMeta(lo1M);
                    e.getInventory().setResult(lo1);
                    break;
                }
            }
        }
}
