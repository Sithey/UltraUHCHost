package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Timer implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cTimer";
    }

    @Override
    public Supplier<ItemStack[]> getContents(Player player) {
        ItemStack[] slots = new ItemStack[getSlots()];
        for (int i = 0; i <= 8; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        for (int i = 36; i <= 44; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }
        /*for (int i = 45; i <= 53; i++){
            slots[i] = new ItemCreator(Material.STAINED_GLASS_PANE).setDurability(14).setName("").getItem();
        }*/
        slots[10] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 minute").setDurability(14).getItem();
        slots[11] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-30 secondes").setDurability(1).getItem();
        slots[12] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 secondes").setDurability(4).getItem();
        slots[13] = new ItemCreator(Material.POTION).setName("§6≡ §eFinal heal : " + Time.FINALHEAL.getMinute() + " minutes " + Time.FINALHEAL.getSeconde() + " secondes ").getItem();
        slots[14] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 seconde").setDurability(12).getItem();
        slots[15] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+30 secondes").setDurability(13).getItem();
        slots[16] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 minute").setDurability(5).getItem();


        slots[19] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 minute").setDurability(14).getItem();
        slots[20] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-30 secondes").setDurability(1).getItem();
        slots[21] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 secondes").setDurability(4).getItem();
        slots[22] = new ItemCreator(Material.DIAMOND_SWORD).setName("§6≡ §ePvP : " + Time.PVP.getMinute() + " minutes " + Time.PVP.getSeconde()+ " secondes ").getItem();
        slots[23] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 seconde").setDurability(12).getItem();
        slots[24] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+30 secondes").setDurability(13).getItem();
        slots[25] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 minute").setDurability(5).getItem();


        slots[28] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 minute").setDurability(14).getItem();
        slots[29] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-30 secondes").setDurability(1).getItem();
        slots[30] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e-1 secondes").setDurability(4).getItem();
        slots[31] = new ItemCreator(Material.BEDROCK).setName("§6≡ §eMeetup : " + Time.MEETUP.getMinute() + " minutes " + Time.MEETUP.getSeconde() + " secondes ").getItem();
        slots[32] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 seconde").setDurability(12).getItem();
        slots[33] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+30 secondes").setDurability(13).getItem();
        slots[34] = new ItemCreator(Material.STAINED_CLAY).setName("§6≡ §e+1 minute").setDurability(5).getItem();

        slots[40] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {

        if (clickedItem.getItemMeta() != null && clickedItem.getItemMeta().getDisplayName() != null){
            if (slot == 40){
                Main.getInstance().getGui().open(player, Configuration.class);
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("-1 minute")){
                if (slot == 10){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() - 60);
                }
                if (slot == 19){
                    Time.PVP.setTime(Time.PVP.getTime() - 60);
                }
                if (slot == 28){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() - 60);
                }
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("-30 secondes")){
                if (slot == 11){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() - 30);
                }
                if (slot == 20){
                    Time.PVP.setTime(Time.PVP.getTime() - 30);
                }
                if (slot == 29){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() - 30);
                }
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("-1 seconde")){
                if (slot == 12){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() - 1);
                }
                if (slot == 21){
                    Time.PVP.setTime(Time.PVP.getTime() - 1);
                }
                if (slot == 30){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() - 1);
                }
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("+1 seconde")){
                if (slot == 14){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() + 1);
                }
                if (slot == 23){
                    Time.PVP.setTime(Time.PVP.getTime() + 1);
                }
                if (slot == 32){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() + 1);
                }
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("+30 secondes")){
                if (slot == 15){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() + 30);
                }
                if (slot == 24){
                    Time.PVP.setTime(Time.PVP.getTime() + 30);
                }
                if (slot == 33){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() + 30);
                }
            }
            if (clickedItem.getItemMeta().getDisplayName().contains("+1 minute")){
                if (slot == 16){
                    Time.FINALHEAL.setTime(Time.FINALHEAL.getTime() + 60);
                }
                if (slot == 25){
                    Time.PVP.setTime(Time.PVP.getTime() + 60);
                }
                if (slot == 34){
                    Time.MEETUP.setTime(Time.MEETUP.getTime() + 60);
                }
            }
            Main.getInstance().getGui().open(player, Timer.class);
            if (slot == 40){
                Main.getInstance().getGui().open(player, Configuration.class);
            }
        }


    }

    @Override
    public int getRows() {
        return 5;
    }
}
