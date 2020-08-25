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

public class Rules implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cRules";
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
        slots[9] = new ItemCreator(Material.BRICK_STAIRS).setName("§6≡ §eRoller Caoster : " + RuleBoolean.ROLLERCAOSTER.isEnabled()).setAmount(RuleBoolean.ROLLERCAOSTER.isEnabled() ? 1 : 0).getItem();
        slots[10] = new ItemCreator(Material.GOLD_PICKAXE).setName("§6≡ §eStrip Mining : " + RuleBoolean.STIPMINING.isEnabled()).setAmount(RuleBoolean.STIPMINING.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.DIAMOND_PICKAXE).setName("§6≡ §ePoke Holling : " + RuleBoolean.POKEHOLLING.isEnabled()).setAmount(RuleBoolean.POKEHOLLING.isEnabled() ? 1 : 0).getItem();
        slots[12] = new ItemCreator(Material.GOLD_HOE).setName("§6≡ §eDig Down at meetup : " + RuleBoolean.DIGDOWNATMEETUP.isEnabled()).setAmount(RuleBoolean.DIGDOWNATMEETUP.isEnabled() ? 1 : 0).getItem();
        slots[13] = new ItemCreator(Material.DIAMOND_SWORD).setName("§6≡ §eStalk : " + RuleBoolean.STALK.isEnabled()).setAmount(RuleBoolean.STALK.isEnabled() ? 1 : 0).getItem();
        slots[14] = new ItemCreator(Material.TRAP_DOOR).setName("§6≡ §eTrap : " + RuleBoolean.TRAP.isEnabled()).setAmount(RuleBoolean.TRAP.isEnabled() ? 1 : 0).getItem();
        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9)
            RuleBoolean.ROLLERCAOSTER.setEnabled(!RuleBoolean.ROLLERCAOSTER.isEnabled());
        if (slot == 10)
            RuleBoolean.STIPMINING.setEnabled(!RuleBoolean.STIPMINING.isEnabled());
        if (slot == 11)
            RuleBoolean.POKEHOLLING.setEnabled(!RuleBoolean.POKEHOLLING.isEnabled());
        if (slot == 12)
            RuleBoolean.DIGDOWNATMEETUP.setEnabled(!RuleBoolean.DIGDOWNATMEETUP.isEnabled());
        if (slot == 13)
            RuleBoolean.STALK.setEnabled(!RuleBoolean.STALK.isEnabled());
        if (slot == 14)
            RuleBoolean.TRAP.setEnabled(!RuleBoolean.TRAP.isEnabled());

            Main.getInstance().getGui().open(player, Rules.class);
            if (slot == 22) {
                Main.getInstance().getGui().open(player, Configuration.class);
            }
    }

    @Override
    public int getRows() {
        return 3;
    }
}
