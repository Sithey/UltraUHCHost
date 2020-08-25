package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.InventoryUtils;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.lib.JsonMessage;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Inventory implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cInventory";
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
        slots[9] = new ItemCreator(Material.CHEST).setName("§6≡ §eStarter Inventory").getItem();
        slots[10] = new ItemCreator(Material.ENDER_CHEST).setName("§6≡ §eDeath Inventory").getItem();
        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, org.bukkit.inventory.Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9){
            player.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
            player.getInventory().clear();
            InventoryUtils.giveStarter(player);
            new JsonMessage().append(Message.PREFIX.getMessage() + "Use /saveitems starter for saving the starter inventory or click here").setClickAsExecuteCmd("/saveitems starter").save().send(player);
        }
        if (slot == 10){
            player.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
            player.getInventory().clear();
            InventoryUtils.giveDeath(player);
            new JsonMessage().append(Message.PREFIX.getMessage() + "Use /saveitems death for saving the death inventory or click here").setClickAsExecuteCmd("/saveitems death").save().send(player);
        }

        if (slot == 22){
            Main.getInstance().getGui().open(player, Configuration.class);
        }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
