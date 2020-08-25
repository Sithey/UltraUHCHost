package fr.sithey.ultrauhchost.lib;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class InventoryUtils {
    private static ItemStack[] starter = new ItemStack[40];
    private static ItemStack[] death = new ItemStack[40];

    public static ItemStack[] getStarter() {
        return starter;
    }

    public static ItemStack[] getDeath() {
        return death;
    }

    public static void saveStarter(Player player){
        for (int slot = 0; slot < 36; slot++) {
            ItemStack item = player.getInventory().getItem(slot);
            if (item != null) {
                getStarter()[slot] = item;
            }
        }

        getStarter()[36] = player.getInventory().getHelmet();
        getStarter()[37] = player.getInventory().getChestplate();
        getStarter()[38] = player.getInventory().getLeggings();
        getStarter()[39] = player.getInventory().getBoots();

        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void saveDeath(Player player){
        for (int slot = 0; slot < 36; slot++) {
            ItemStack item = player.getInventory().getItem(slot);
            if (item != null) {
                getDeath()[slot] = item;
            }
        }

        getDeath()[36] = player.getInventory().getHelmet();
        getDeath()[37] = player.getInventory().getChestplate();
        getDeath()[38] = player.getInventory().getLeggings();
        getDeath()[39] = player.getInventory().getBoots();

        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
  }

  public static void giveStarter(Player player){
      player.getInventory().clear();
      player.getInventory().setHelmet(null);
      player.getInventory().setChestplate(null);
      player.getInventory().setLeggings(null);
      player.getInventory().setBoots(null);

      for (int slot = 0; slot < 36; slot++) {
          ItemStack item = getStarter()[slot];
          if (item != null) {
              player.getInventory().setItem(slot, item);
          }
      }

      player.getInventory().setHelmet(getStarter()[36]);
      player.getInventory().setChestplate(getStarter()[37]);
      player.getInventory().setLeggings(getStarter()[38]);
      player.getInventory().setBoots(getStarter()[39]);
  }
    public static void giveDeath(Player player){
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        for (int slot = 0; slot < 36; slot++) {
            ItemStack item = getDeath()[slot];
            if (item != null) {
                player.getInventory().setItem(slot, item);
            }
        }

        player.getInventory().setHelmet(getDeath()[36]);
        player.getInventory().setChestplate(getDeath()[37]);
        player.getInventory().setLeggings(getDeath()[38]);
        player.getInventory().setBoots(getDeath()[39]);
    }

    public static void dropDeath(Location location){
        for (int slot = 0; slot <= 39; slot++) {
            ItemStack item = getDeath()[slot];
            if (item != null) {
                location.getWorld().dropItem(location, item).setPickupDelay(0);
            }
        }
    }

    public static void addToChest(Inventory inventory){
        List<ItemStack> list = Arrays.asList(death);
        for (ItemStack l : list){
            if (l != null && !l.getType().equals(Material.AIR))
            inventory.addItem(l);
        }
    }
}
