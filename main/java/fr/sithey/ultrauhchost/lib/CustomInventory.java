package fr.sithey.ultrauhchost.lib;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public interface CustomInventory {

	String getName();

	Supplier<ItemStack[]> getContents(Player player);

	void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type);

	int getRows();

	default int getSlots() {
		return getRows() * 9;
	}
}
