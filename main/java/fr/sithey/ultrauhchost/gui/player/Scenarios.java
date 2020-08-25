package fr.sithey.ultrauhchost.gui.player;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.gui.host.Configuration;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Scenarios implements CustomInventory {
    private List<Player> hoststart = new ArrayList<>();
    @Override
    public String getName() {
        return "§7● §cScenarios";
    }

    @Override
    public Supplier<ItemStack[]> getContents(Player player) {
        ItemStack[] slots = new ItemStack[getSlots()];
        for (int i = 0; i <= 8; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        for (int i = 45; i <= 53; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        int i = 9;
        for (Scenario scenario : Scenario.getEnabled()){
            slots[i] = scenario.getScenario().getItem();
            i++;
        }
        slots[49] = new ItemCreator(Material.ARROW).setName("Back").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 49)
            player.performCommand("rules");
    }

    @Override
    public int getRows() {
        return 6;
    }
}
