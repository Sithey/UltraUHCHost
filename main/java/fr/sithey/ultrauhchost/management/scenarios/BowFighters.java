package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BowFighters extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Bow Fighters";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.BOWFIGHTERS.getDescription().split(" ")){
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
        return new ItemCreator(Material.BOW).setName(getName() + " : " + Scenario.BOWFIGHTERS.isEnabled()).setAmount(Scenario.BOWFIGHTERS.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.ARROW).setAmount(2).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.STRING).setAmount(2).getItem());
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.ENCHANTED_BOOK).addStoredEnchantment(Enchantment.ARROW_INFINITE, 1).getItem());

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
        return "bowfighters";
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraft(final CraftItemEvent craftItemEvent)  {
        if(craftItemEvent.getCurrentItem().getType().equals(Material.STONE_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.IRON_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_SWORD)
                || craftItemEvent.getCurrentItem().getType().equals(Material.IRON_AXE)
                || craftItemEvent.getCurrentItem().getType().equals(Material.DIAMOND_AXE)) {
            craftItemEvent.setCancelled(true);

            Player player = Bukkit.getServer().getPlayer(craftItemEvent.getWhoClicked().getName());

            if (player != null) {
                player.sendMessage(Message.PREFIX.getMessage() + "You can't craft that in bowfighters!");
            }
        }
    }
}
