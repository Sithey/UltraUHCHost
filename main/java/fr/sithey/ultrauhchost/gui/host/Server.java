package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Server implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cServer";
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
        slots[9] = new ItemCreator(Material.NAME_TAG).setName("§6≡ §eWhitelist : "+ Bukkit.getServer().hasWhitelist()).setAmount(Bukkit.getServer().hasWhitelist() ? 1 : 0).getItem();
        slots[10] = new ItemCreator(Material.SKULL_ITEM).setName("§6≡ §eSpec : " + RuleBoolean.SPEC.isEnabled()).setAmount(RuleBoolean.SPEC.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.SKULL_ITEM).setDurability(3).setOwner("Sithey").setName("§6≡ §eHost play : " + RuleBoolean.HOSTPLAY.isEnabled()).setAmount(RuleBoolean.HOSTPLAY.isEnabled() ? 1 : 0).getItem();
        slots[12] = new ItemCreator(Material.PAPER).setName("§6≡ §eSlots : " + RuleInteger.SLOTS.getValue()).getItem();
        slots[13] = new ItemCreator(Material.DIAMOND_SWORD).setName("§6≡ §eLateScatter : " + RuleBoolean.LATESCATTER.isEnabled()).setAmount(RuleBoolean.LATESCATTER.isEnabled() ? 1 : 0).getItem();


        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (type.isLeftClick()){
            if (slot == 12){
                if (RuleInteger.SLOTS.getValue() != 1)
                RuleInteger.SLOTS.setValue(RuleInteger.SLOTS.getValue() - 1);
            }
        }
        if (type.isRightClick()){
            if (slot == 12){
                RuleInteger.SLOTS.setValue(RuleInteger.SLOTS.getValue() + 1);
            }
        }
        if (slot == 9)
            Bukkit.getServer().setWhitelist(!Bukkit.getServer().hasWhitelist());
        if (slot == 10)
            RuleBoolean.SPEC.setEnabled(!RuleBoolean.SPEC.isEnabled());
        if (slot == 11)
            RuleBoolean.HOSTPLAY.setEnabled(!RuleBoolean.HOSTPLAY.isEnabled());
        if (slot == 13)
            RuleBoolean.LATESCATTER.setEnabled(!RuleBoolean.LATESCATTER.isEnabled());

        Main.getInstance().getGui().open(player, Server.class);
        if (slot == 22){
            Main.getInstance().getGui().open(player, Configuration.class);
        }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
