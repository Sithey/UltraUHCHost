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

public class Nether implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cNether";
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
        slots[9] = new ItemCreator(Material.OBSIDIAN).setName("§6≡ §eNether : " + RuleBoolean.NETHER.isEnabled()).setAmount(RuleBoolean.NETHER.isEnabled() ? 1 : 0).getItem();
        slots[10] = new ItemCreator(Material.POTION).setName("§6≡ §ePotion : " + RuleBoolean.POTION.isEnabled()).setAmount(RuleBoolean.POTION.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.BLAZE_POWDER).setName("§6≡ §eStrength : " + RuleBoolean.STRENGTH.isEnabled()).setAmount(RuleBoolean.STRENGTH.isEnabled() ? 1 : 0).getItem();
        slots[12] = new ItemCreator(Material.SUGAR).setName("§6≡ §eSpeed : " + RuleBoolean.SPEED.isEnabled()).setAmount(RuleBoolean.SPEED.isEnabled() ? 1 : 0).getItem();
        slots[13] = new ItemCreator(Material.FERMENTED_SPIDER_EYE).setName("§6≡ §ePoison : " + RuleBoolean.POISON.isEnabled()).setAmount(RuleBoolean.POISON.isEnabled() ? 1 : 0).getItem();
        slots[14] = new ItemCreator(Material.GOLDEN_CARROT).setName("§6≡ §eInvisibility : " + RuleBoolean.INVISIBILITY.isEnabled()).setAmount(RuleBoolean.INVISIBILITY.isEnabled() ? 1 : 0).getItem();
        slots[15] = new ItemCreator(Material.SPECKLED_MELON).setName("§6≡ §eHeal : " + RuleBoolean.HEAL.isEnabled()).setAmount(RuleBoolean.HEAL.isEnabled() ? 1 : 0).getItem();
        slots[16] = new ItemCreator(Material.GHAST_TEAR).setName("§6≡ §eRegen : " + RuleBoolean.REGEN.isEnabled()).setAmount(RuleBoolean.REGEN.isEnabled() ? 1 : 0).getItem();
        slots[17] = new ItemCreator(Material.GLOWSTONE_DUST).setName("§6≡ §eLevel II : " + RuleBoolean.LEVEL2.isEnabled()).setAmount(RuleBoolean.LEVEL2.isEnabled() ? 1 : 0).getItem();


        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9)
            RuleBoolean.NETHER.setEnabled(!RuleBoolean.NETHER.isEnabled());
        if (slot == 10)
            RuleBoolean.POTION.setEnabled(!RuleBoolean.POTION.isEnabled());
        if (slot == 11)
            RuleBoolean.STRENGTH.setEnabled(!RuleBoolean.STRENGTH.isEnabled());
        if (slot == 12)
            RuleBoolean.SPEED.setEnabled(!RuleBoolean.SPEED.isEnabled());
        if (slot == 13)
            RuleBoolean.POISON.setEnabled(!RuleBoolean.POISON.isEnabled());
        if (slot == 14)
            RuleBoolean.INVISIBILITY.setEnabled(!RuleBoolean.INVISIBILITY.isEnabled());
        if (slot == 15)
            RuleBoolean.HEAL.setEnabled(!RuleBoolean.HEAL.isEnabled());
        if (slot == 16)
            RuleBoolean.REGEN.setEnabled(!RuleBoolean.REGEN.isEnabled());
        if (slot == 17)
            RuleBoolean.LEVEL2.setEnabled(!RuleBoolean.LEVEL2.isEnabled());
            Main.getInstance().getGui().open(player, Nether.class);
            if (slot == 22){
                Main.getInstance().getGui().open(player, Configuration.class);
            }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
