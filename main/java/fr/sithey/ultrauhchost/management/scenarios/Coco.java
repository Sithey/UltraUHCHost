package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Coco extends CustomScenario implements Listener {
    @Override
    public String getName() {
        return "Coco";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.COCO.getDescription().split(" ")){
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
        return new ItemCreator(Material.getMaterial(351)).setDurability(3).setName(getName() + " : " + Scenario.COCO.isEnabled()).setAmount(Scenario.COCO.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {
        player.getPlayer().getInventory().addItem(new ItemCreator(Material.getMaterial(351)).setDurability(3).setAmount(5).getItem());
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
        return "coco";
    }
    private List<Player> cache = new ArrayList<>();
    @EventHandler
    public void onCocoa(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Material mat = event.getMaterial();

        if (action.equals(Action.PHYSICAL) || item == null || mat.equals(Material.AIR) || cache.contains(player))
            return;

        if (item.getType().equals(Material.getMaterial(351)) && item.getDurability() == ((short) 3)){
            if (item.getAmount() != 1){
                item.setAmount(item.getAmount() - 1);
            }else{
                event.getPlayer().setItemInHand(null);
            }
            cache.add(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 30 * 20, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10 * 20, 0, false, false));
            new BukkitRunnable() {
                @Override
                public void run() {
                    cache.remove(player);
                    if (Bukkit.getPlayer(player.getUniqueId()) != null) {
                        Bukkit.getPlayer(player.getUniqueId()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 15 * 20, 0, false, false));
                        Bukkit.getPlayer(player.getUniqueId()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 5 * 20, 0, false, false));
                    }
                }
            }.runTaskLater(Main.getInstance(), 30 * 20);
        }
    }
}
