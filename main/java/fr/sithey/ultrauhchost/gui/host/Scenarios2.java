package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Scenarios2 implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cScenarios2";
    }

    @Override
    public Supplier<ItemStack[]> getContents(Player player) {
        ItemStack[] slots = new ItemStack[getSlots()];
        for (int i = 0; i <= 8; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        slots[3] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(4).setName("§6≡ §ePage 1").getItem();
        slots[5] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(4).setName("§6≡ §ePage 3").getItem();
        for (int i = 45; i <= 53; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        int i = 9;
        for (Scenario scenario : Scenario.values()){
            if (i >= 36 + 9 && i < 81) {
                slots[i - 36] = scenario.getScenario().getItem();
            }
            i++;
        }
        slots[49] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (clickedItem.getItemMeta() == null || clickedItem.getItemMeta().getDisplayName() == null)
            return;
        for (Scenario scenario : Scenario.values()){
            if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(scenario.getScenario().getItem().getItemMeta().getDisplayName()))
                if (scenario.isPremium()){
                    Main.getInstance().sendPremiumMessage(player);
                }else {
                    scenario.toggleEnabled();
                }
        }
        Main.getInstance().getGui().open(player, Scenarios2.class);
        if (slot == 49){
            Main.getInstance().getGui().open(player, Configuration.class);
        }
        if (slot == 3){
            Main.getInstance().getGui().open(player, Scenarios1.class);
        }
        if (slot == 5){
            Main.getInstance().getGui().open(player, Scenarios3.class);
        }
    }

    @Override
    public int getRows() {
        return 6;
    }
}
