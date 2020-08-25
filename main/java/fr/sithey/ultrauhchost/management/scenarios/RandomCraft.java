package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.*;

public class RandomCraft extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "RandomCraft";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.RANDOMCRAFT.getDescription().split(" ")){
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
        return new ItemCreator(Material.WORKBENCH).setName(getName() + " : " + Scenario.RANDOMCRAFT.isEnabled()).setAmount(Scenario.RANDOMCRAFT.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        return "randomcraft";
    }

    private Map<ItemStack, ItemStack> cache = new HashMap<>();

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event){
        if (cache.get(event.getInventory().getResult()) != null){
            event.getInventory().setResult(cache.get(event.getInventory().getResult()));
        }else{
            checkDrop(event.getInventory().getResult());
            event.getInventory().setResult(cache.get(event.getInventory().getResult()));
        }
    }

    @EventHandler
    public void onPrepareFurnaceItem(FurnaceSmeltEvent event){
        if (cache.get(event.getResult()) != null){
            event.setResult(cache.get(event.getResult()));
        }else{
            checkDrop(event.getResult());
            event.setResult(cache.get(event.getResult()));
        }
    }

    private void checkDrop(ItemStack itemStack){

        Material randomMaterial = Material.values()[new Random().nextInt(Material.values().length)];
        if(isAcceptedMaterial(randomMaterial)){

            if(isRarity(randomMaterial)){
                int amount = 1;
                if(randomMaterial == Material.GOLDEN_APPLE)
                    amount = 3;
                if(randomMaterial == Material.IRON_INGOT || randomMaterial == Material.GOLD_INGOT)
                    amount = 16;
                ItemStack item = new ItemStack(randomMaterial, amount);

                if(randomMaterial == Material.ENCHANTED_BOOK){
                    EnchantmentStorageMeta meta = (EnchantmentStorageMeta)item.getItemMeta();
                    Enchantment randomEnchant = Enchantment.values()[new Random().nextInt(Enchantment.values().length)];
                    int randomLevel = new Random().nextInt(3);
                    meta.addStoredEnchant(randomEnchant, randomLevel+1, true);
                    item.setItemMeta(meta);
                }
                alreadyused.add(item);
                cache.put(itemStack, item);

            } else {

                ItemStack item = new ItemCreator(randomMaterial).getItem();
                alreadyused.add(item);
                cache.put(itemStack, item);
            }
        } else {
            checkDrop(itemStack);
        }
    }
    private List<ItemStack> alreadyused = new ArrayList<>();
    private boolean isAcceptedMaterial(Material type){
        if (alreadyused.contains(type)){
            return false;
        }
        if(type == Material.AIR || type == Material.ARMOR_STAND || type == Material.BEDROCK || type == Material.ACACIA_DOOR || type == Material.BIRCH_DOOR_ITEM || type == Material.BREWING_STAND
                || type == Material.BURNING_FURNACE || type == Material.CAKE_BLOCK || type == Material.CAULDRON || type == Material.COMMAND || type == Material.COMMAND_MINECART || type == Material.DARK_OAK_DOOR
                || type == Material.DIODE_BLOCK_OFF || type == Material.DIODE_BLOCK_ON || type == Material.ENDER_PORTAL || type == Material.ENDER_PORTAL_FRAME || type == Material.FLOWER_POT || type == Material.GLOWING_REDSTONE_ORE
                || type == Material.POWERED_MINECART || type == Material.HOPPER_MINECART || type == Material.PISTON_EXTENSION || type == Material.PISTON_MOVING_PIECE || type == Material.PORTAL || type == Material.SKULL || type == Material.STATIONARY_LAVA
                || type == Material.STATIONARY_WATER || type == Material.SUGAR_CANE_BLOCK || type == Material.WATER || type == Material.LAVA || type == Material.BARRIER || type == Material.BED || type == Material.BED_BLOCK || type == Material.MOB_SPAWNER
                || type == Material.MONSTER_EGG || type == Material.MONSTER_EGGS || type == Material.WRITTEN_BOOK || type == Material.EXPLOSIVE_MINECART || type == Material.SOIL){
            return false;
        }
        return true;
    }

    private boolean isRarity(Material type){
        if (alreadyused.contains(type)){
            return false;
        }
        if(type == Material.ANVIL || type == Material.BEACON || type == Material.BREWING_STAND_ITEM || type == Material.CHAINMAIL_BOOTS || type == Material.CHAINMAIL_CHESTPLATE || type == Material.CHAINMAIL_LEGGINGS || type == Material.CHAINMAIL_HELMET || type == Material.LEATHER_BOOTS || type == Material.LEATHER_CHESTPLATE || type == Material.LEATHER_LEGGINGS || type == Material.LEATHER_HELMET || type == Material.DIAMOND || type == Material.DIAMOND_AXE || type == Material.DIAMOND_BARDING || type == Material.DIAMOND_BLOCK || type == Material.DIAMOND_BOOTS || type == Material.DIAMOND_CHESTPLATE || type == Material.DIAMOND_HELMET || type == Material.DIAMOND_HOE || type == Material.DIAMOND_LEGGINGS || type == Material.DIAMOND_ORE || type == Material.DIAMOND_PICKAXE || type == Material.DIAMOND_SPADE || type == Material.DIAMOND_SWORD || type == Material.IRON_INGOT || type == Material.IRON_AXE || type == Material.IRON_BARDING || type == Material.IRON_BLOCK || type == Material.IRON_BOOTS || type == Material.IRON_CHESTPLATE || type == Material.IRON_HELMET || type == Material.IRON_HOE || type == Material.IRON_LEGGINGS || type == Material.IRON_ORE || type == Material.IRON_PICKAXE || type == Material.IRON_SPADE || type == Material.IRON_SWORD || type == Material.GOLD_INGOT || type == Material.GOLD_AXE || type == Material.GOLD_BARDING || type == Material.GOLD_BLOCK || type == Material.GOLD_BOOTS || type == Material.GOLD_CHESTPLATE || type == Material.GOLD_HELMET || type == Material.GOLD_HOE || type == Material.GOLD_LEGGINGS || type == Material.GOLD_ORE || type == Material.GOLD_PICKAXE || type == Material.GOLD_SPADE || type == Material.GOLD_SWORD || type == Material.DRAGON_EGG || type == Material.ENDER_PEARL || type == Material.FISHING_ROD || type == Material.LAVA_BUCKET || type == Material.WATER_BUCKET || type == Material.ENCHANTED_BOOK || type == Material.ENCHANTMENT_TABLE || type == Material.RECORD_10 || type == Material.RECORD_11 || type == Material.RECORD_12 || type == Material.RECORD_3 || type == Material.RECORD_4 || type == Material.RECORD_5 || type == Material.RECORD_6 || type == Material.RECORD_7 || type == Material.RECORD_8 || type == Material.RECORD_9 || type == Material.FLINT_AND_STEEL || type == Material.CAKE || type == Material.STONE_PICKAXE || type == Material.STONE_SPADE || type == Material.STONE_SWORD || type == Material.STONE_HOE || type == Material.STONE_AXE || type == Material.WOOD_PICKAXE || type == Material.WOOD_SPADE || type == Material.WOOD_SWORD || type == Material.WOOD_HOE || type == Material.WOOD_AXE || type == Material.RABBIT_STEW || type == Material.MUSHROOM_SOUP || type == Material.CARROT_STICK || type == Material.MILK_BUCKET || type == Material.POTION || type == Material.GOLDEN_APPLE || type == Material.SADDLE || type == Material.BONE || type == Material.INK_SACK || type == Material.BOW || type == Material.BOWL){
            return true;
        }
        return false;
    }

}
