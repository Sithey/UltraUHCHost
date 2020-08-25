package fr.sithey.ultrauhchost.listener;

import com.google.common.collect.Sets;
import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.host.Configuration;
import fr.sithey.ultrauhchost.gui.player.Rule;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class PlayerInventory implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        org.bukkit.inventory.Inventory inv = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if (current == null) {
            return;
        }

        if (Main.getInstance().getUHC().getStatut().isLobby()) {
            if (!player.getGameMode().equals(GameMode.CREATIVE))
                event.setCancelled(true);
        }
        else{
            if (!player.getGameMode().equals(GameMode.SURVIVAL))
                event.setCancelled(true);
        }

        if (event.getClick() != null) {
            Main.getInstance().getGui().registeredMenus.values().stream().filter(menu -> inv.getName().equalsIgnoreCase(menu.getName()))
                    .forEach(menu -> {
                        menu.onClick(player, inv, current, event.getSlot(), event.getClick());
                        event.setCancelled(true);
                    });
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Material mat = event.getMaterial();

        if (action.equals(Action.PHYSICAL) || item == null || mat.equals(Material.AIR) || item.getItemMeta().getDisplayName() == null)
            return;

        if (Main.getInstance().getUHC().getStatut().isLobby()){
            if (item.getType().equals(Material.ENCHANTED_BOOK)){
                Main.getInstance().getGui().open(player, Configuration.class);
            }
            if (item.getType().equals(Material.BOOK)){
                Main.getInstance().getGui().open(player, Rule.class);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        if (!Main.getInstance().getUHC().getMessage().getConfig().getBoolean("chat.enabled"))
            return;
        Player player = event.getPlayer();
        UPlayer pl = UPlayer.getUPlayer(player.getUniqueId());
        UTeam team = UTeam.getTeamWithPlayer(player);
        if (UPlayer.getHost == pl){
            String format = Message.CHAT.getMessage();
            event.setFormat(format.replace("<player>", Message.HOST.getMessage() + player.getName()).replace("<chat>", event.getMessage()));
            return;
        }
        if (!pl.isPlaying()){
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                String format = Message.CHAT.getMessage();
                event.setFormat(format.replace("<player>", Message.SPEC.getMessage() + player.getName()).replace("<chat>", event.getMessage()));
            }else{
                String format = Message.CHAT.getMessage();
                event.setFormat(format.replace("<player>", Message.DEATH.getMessage() + player.getName()).replace("<chat>", event.getMessage()));
            }
        }else {
            String format = Message.CHAT.getMessage();
            event.setFormat(format.replace("<player>", (team == null ? "" : team.getPrefix()) + player.getName()).replace("<chat>", event.getMessage()));
        }
    }


    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        Player player = e.getPlayer();
        Set<String> commands = Sets.newHashSet("/me", "/bukkit:me", "/minecraft:me");

        if(commands.stream().anyMatch(e.getMessage()::startsWith)){
            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1f, 1f);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteractPlayer(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }
        Player player = event.getPlayer();
        Player target = (Player) event.getRightClicked();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 54, "§7§l◆ §4Inv -> §c" + target.getDisplayName());
            for (int i = 0; i < 36; i++) {
                if (target.getInventory().getItem(i) != null) {
                    inv.setItem(i, target.getInventory().getItem(i));
                }
            }
            for (int i = 36; i < 45; i++) {
                inv.setItem(i, new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(1).getItem());
            }
            inv.setItem(45, target.getInventory().getHelmet());
            inv.setItem(46, target.getInventory().getChestplate());
            inv.setItem(47, target.getInventory().getLeggings());
            inv.setItem(48, target.getInventory().getBoots());
            inv.setItem(53, new ItemCreator(Material.PAPER).setName("§cInformation").addLore("§7● Vie: §a" + ((int) target.getHealth())).addLore("§7● Food: §a" + ((int) target.getFoodLevel())).addLore("§7● Diamond: §a" + UPlayer.getUPlayer(target.getUniqueId()).getDiamonds()).addLore("§7● Gold: §a" + UPlayer.getUPlayer(target.getUniqueId()).getGolds()).getItem());

            player.openInventory(inv);
        }

    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event){
        if (event.getItem() != null && event.getItem().getType().equals(Material.GOLDEN_APPLE)  && event.getItem().getItemMeta().getDisplayName() != null && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Golden Head")){
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        }

    }
}
