package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class Teams implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cTeams";
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
        slots[9] = new ItemCreator(Material.BANNER).setName("§6≡ §eSize : "+ RuleInteger.TEAMSIZE.getValue()).getItem();
        slots[10] = new ItemCreator(Material.SKULL_ITEM).setName("§6≡ §eFriendly fire : " + RuleBoolean.FRIENDLYFIRE.isEnabled()).setAmount(RuleBoolean.FLINTANDSTEEL.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.BLAZE_POWDER).setName("§6≡ §eRandom Team : " + RuleBoolean.RANDOMTEAM.isEnabled()).setAmount(RuleBoolean.RANDOMTEAM.isEnabled() ? 1 : 0).getItem();
        slots[12] = new ItemCreator(Material.PAPER).setName("§6≡ §eReset").getItem();

        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (type.isLeftClick()){
            if (slot == 9){
                if (RuleInteger.TEAMSIZE.getValue() != 1)
                RuleInteger.TEAMSIZE.setValue(RuleInteger.TEAMSIZE.getValue() - 1);
            }
        }
        if (type.isRightClick()){
            if (slot == 9){
                RuleInteger.TEAMSIZE.setValue(RuleInteger.TEAMSIZE.getValue() + 1);
            }
        }
        if (slot == 10) {
            RuleBoolean.FRIENDLYFIRE.setEnabled(!RuleBoolean.FRIENDLYFIRE.isEnabled());
        }
        if (slot == 11) {
            RuleBoolean.RANDOMTEAM.setEnabled(!RuleBoolean.RANDOMTEAM.isEnabled());
            UTeam.reset();
        }
        if (slot == 12)
            UTeam.reset();
        Main.getInstance().getGui().open(player, Teams.class);
        if (slot == 22){
            Main.getInstance().getGui().open(player, Configuration.class);
        }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
