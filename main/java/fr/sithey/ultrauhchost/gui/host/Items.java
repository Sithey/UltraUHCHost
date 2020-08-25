package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Items implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cItems";
    }

    @Override
    public Supplier<ItemStack[]> getContents(Player player) {
        ItemStack[] slots = new ItemStack[getSlots()];
        for (int i = 0; i <= 8; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        for (int i = 18; i <= 26; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        slots[9] = new ItemCreator(Material.GOLD_BLOCK).setName("§6≡ §eNotch Apple : " + RuleBoolean.NOTCHAPPLE.isEnabled()).setAmount(RuleBoolean.NOTCHAPPLE.isEnabled() ? 1 : 0).getItem();
        slots[10] = new ItemCreator(Material.SKULL_ITEM).setOwner(player.getName()).setDurability(3).setName("§6≡ §eHead : " + RuleBoolean.HEAD.isEnabled()).setAmount(RuleBoolean.HEAD.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.GOLDEN_CARROT).setName("§6≡ §eGolden Head to kill : " + RuleBoolean.GHEAD.isEnabled()).setAmount(RuleBoolean.GHEAD.isEnabled() ? 1 : 0).getItem();
        slots[12] = new ItemCreator(Material.GOLDEN_APPLE).setName("§6≡ §eGolden Apple to kill : " + RuleBoolean.GAPPLE.isEnabled()).setAmount(RuleBoolean.GAPPLE.isEnabled() ? 1 : 0).getItem();
        slots[13] = new ItemCreator(Material.DIAMOND_SWORD).setName("§6≡ §eFire aspect : " + RuleBoolean.FIRE_ASPECT.isEnabled()).setAmount(RuleBoolean.FIRE_ASPECT.isEnabled() ? 1 : 0).getItem();
        slots[14] = new ItemCreator(Material.BOW).setName("§6≡ §eFlame : " + RuleBoolean.FLAME.isEnabled()).setAmount(RuleBoolean.FLAME.isEnabled() ? 1 : 0).getItem();
        slots[15] = new ItemCreator(Material.SKULL_ITEM).setDurability(3).setName("§6≡ §eCraft Golden Head : " + RuleBoolean.CRAFTGOLDENHEAD.isEnabled()).setAmount(RuleBoolean.CRAFTGOLDENHEAD.isEnabled() ? 1 : 0).getItem();
        slots[16] = new ItemCreator(Material.STRING).setName("§6≡ §eWool Craft To String : " + RuleBoolean.WOOLTOSTRING.isEnabled()).setAmount(RuleBoolean.WOOLTOSTRING.isEnabled() ? 1 : 0).getItem();
        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9)
            RuleBoolean.NOTCHAPPLE.setEnabled(!RuleBoolean.NOTCHAPPLE.isEnabled());
        if (slot == 10)
            RuleBoolean.HEAD.setEnabled(!RuleBoolean.HEAD.isEnabled());
        if (slot == 11)
            RuleBoolean.GHEAD.setEnabled(!RuleBoolean.GHEAD.isEnabled());
        if (slot == 12)
            RuleBoolean.GAPPLE.setEnabled(!RuleBoolean.GAPPLE.isEnabled());
        if (slot == 13)
            RuleBoolean.FIRE_ASPECT.setEnabled(!RuleBoolean.FIRE_ASPECT.isEnabled());
        if (slot == 14)
            RuleBoolean.FLAME.setEnabled(!RuleBoolean.FLAME.isEnabled());
        if (slot == 15)
            RuleBoolean.CRAFTGOLDENHEAD.setEnabled(!RuleBoolean.CRAFTGOLDENHEAD.isEnabled());
        if (slot == 16)
            RuleBoolean.WOOLTOSTRING.setEnabled(!RuleBoolean.WOOLTOSTRING.isEnabled());
            Main.getInstance().getGui().open(player, Items.class);
            if (slot == 22){
                Main.getInstance().getGui().open(player, Configuration.class);
            }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
