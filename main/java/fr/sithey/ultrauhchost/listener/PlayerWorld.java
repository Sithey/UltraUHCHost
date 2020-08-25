package fr.sithey.ultrauhchost.listener;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerWorld implements Listener {

    @EventHandler (priority = EventPriority.MONITOR)
    public void onPortal(PortalCreateEvent event){
        if (!RuleBoolean.NETHER.isEnabled())
            event.setCancelled(true);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Material mat = event.getMaterial();

        if (action.equals(Action.PHYSICAL) || item == null || mat.equals(Material.AIR) || item.getItemMeta().getDisplayName() == null)
            return;

        if (!Main.getInstance().getUHC().getStatut().isLobby()){
            if (item.getType().equals(Material.FLINT_AND_STEEL) && !RuleBoolean.FLINTANDSTEEL.isEnabled()){
                for (UPlayer p : UPlayer.getAlivePlayers()){
                    if (p.getPlayer().getLocation().distance(player.getLocation()) <= 5){
                        /*
                        CANCEL FLINT
                         */
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onBucket(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (!Main.getInstance().getUHC().getStatut().isLobby()){
            if (event.getItemStack().getType().equals(Material.LAVA_BUCKET) && !RuleBoolean.LAVABUCKET.isEnabled()){
                for (UPlayer p : UPlayer.getAlivePlayers()){
                    if (p.getPlayer().getLocation().distance(player.getLocation()) <= 5){
                        /*
                        CANCEL LAVA
                         */
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onCraftPotion(InventoryClickEvent event){
        ItemStack current = event.getCurrentItem();

        if (current == null) {
            return;
        }

        if (Main.getInstance().getUHC().getStatut().equals(Statut.GAME)) {
            if ((event.getCurrentItem().getType().equals(Material.POTION) && !RuleBoolean.POTION.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.BLAZE_POWDER) && !RuleBoolean.STRENGTH.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.SUGAR) && !RuleBoolean.SPEED.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.GLOWSTONE_DUST) && !RuleBoolean.LEVEL2.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.FERMENTED_SPIDER_EYE) && !RuleBoolean.POISON.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.GOLDEN_CARROT) && !RuleBoolean.INVISIBILITY.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.SPECKLED_MELON) && !RuleBoolean.HEAL.isEnabled()) ||
                    (event.getCurrentItem().getType().equals(Material.GHAST_TEAR) && !RuleBoolean.REGEN.isEnabled())) {
                event.setCurrentItem(new ItemCreator(Material.AIR).getItem());
            /*
            POTION OFF
             */
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event){
        Block Block2 = event.getBlock();
        if (Block2.getType().equals(Material.DIAMOND_ORE)){
            List<Block> filon = new ArrayList<>();
            if(!check.contains(event.getBlock())){
                countBlocks(Material.DIAMOND_ORE, event.getBlock(), filon);
                preventStaff(event.getPlayer(), filon.size() + UPlayer.getUPlayer(event.getPlayer().getUniqueId()).getDiamonds() - 1);

            }
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addDiamond();
            return;
        }
        if (Block2.getType().equals(Material.GOLD_ORE)){
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addGold();
            return;
        }
        if (Block2.getType().equals(Material.EMERALD_ORE)){
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addEmerald();
            return;
        }
        if (Block2.getType().equals(Material.IRON_INGOT)){
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addIron();
            return;
        }
        if (Block2.getType().equals(Material.REDSTONE_ORE)){
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addRedstone();
            return;
        }

        if (Block2.getType().equals(Material.LAPIS_ORE)){
            UPlayer.getUPlayer(event.getPlayer().getUniqueId()).addLapis();
            return;
        }
        Location loc = new Location(Block2.getWorld(), (double)((float)Block2.getLocation().getBlockX() + 0.0f), (double)((float)Block2.getLocation().getBlockY() + 0.0f), (double)((float)Block2.getLocation().getBlockZ() + 0.0f));
        Random random = new Random();
        double r = random.nextDouble();
        if (Block2.getType() == Material.LEAVES && r <= (double) RuleInteger.APPLERATE.getValue() * 0.01) {
            Block2.setType(Material.AIR);
            Block2.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE));
        }

        if (Block2.getType() == Material.GRAVEL && r <= (double) RuleInteger.FLINTRATE.getValue() * 0.01) {
            Block2.setType(Material.AIR);
            Block2.getWorld().dropItemNaturally(loc, new ItemStack(Material.FLINT));
        }
    }


    @EventHandler
    public void appleRate(LeavesDecayEvent event) {
        final Block b = event.getBlock();
        final Location loc = new Location(b.getWorld(), b.getLocation().getBlockX() + 0.0, b.getLocation().getBlockY() + 0.0, b.getLocation().getBlockZ() + 0.0);
        final Random random = new Random();
        final double r = random.nextDouble();
        if (r <= RuleInteger.APPLERATE.getValue() * 0.01 && b.getType() == Material.LEAVES) {
            b.setType(Material.AIR);
            b.getWorld().dropItemNaturally(loc, new ItemStack(Material.APPLE, 1));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Item && !RuleBoolean.ITEMBURN.isEnabled()) {
            e.setCancelled(true);
        }
    }

    private List<Block> check = new ArrayList<Block>();
    private void countBlocks(Material type, Block b, List<Block> filon){
        if(b.getType() != type) return;
        if(filon.contains(b)) return;

        filon.add(b);
        check.add(b);

        countBlocks(type, b.getRelative(BlockFace.DOWN), filon);
        countBlocks(type, b.getRelative(BlockFace.EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.EAST_NORTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.EAST_SOUTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.NORTH), filon);
        countBlocks(type, b.getRelative(BlockFace.NORTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.NORTH_NORTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.NORTH_NORTH_WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.NORTH_WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.SOUTH), filon);
        countBlocks(type, b.getRelative(BlockFace.SOUTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.SOUTH_SOUTH_EAST), filon);
        countBlocks(type, b.getRelative(BlockFace.SOUTH_SOUTH_WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.SOUTH_WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.UP), filon);
        countBlocks(type, b.getRelative(BlockFace.WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.WEST_NORTH_WEST), filon);
        countBlocks(type, b.getRelative(BlockFace.WEST_SOUTH_WEST), filon);
    }

    private void preventStaff(Player p, int nombre){
        UPlayer uPlayer = UPlayer.getUPlayer(p.getUniqueId());
        for(UPlayer up : UPlayer.getOnlinePlayers()){
            if(!up.isPlaying() && up.getPlayer().getGameMode().equals(GameMode.SPECTATOR) && up.getPlayer() != null){

                TextComponent message = new TextComponent("§4§lXRAY §6» §c"+uPlayer.getPlayer().getName()+" §b " + nombre+" diamonds");

                int diamondMining = UPlayer.getUPlayer(p.getUniqueId()).getDiamonds();
                if(diamondMining!= 0) message.addExtra(" §8[§6" + diamondMining + "§8]");

                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teleport to " + uPlayer.getPlayer().getName()).create()));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpto "+uPlayer.getPlayer().getName()));

                up.getPlayer().spigot().sendMessage(message);
            }
        }

    }
}
