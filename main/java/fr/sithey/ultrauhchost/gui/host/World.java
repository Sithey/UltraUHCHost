package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.task.LoadingChunkTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class World implements CustomInventory {
    @Override
    public String getName() {
        return "§7● §cWorld";
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
        slots[9] = new ItemCreator(Material.GRASS).setName("§6≡ §eWorld chunck loaded : "+ RuleBoolean.WORLDLOAD.isEnabled()).setAmount(RuleBoolean.WORLDLOAD.isEnabled() ? 1 : 0).getItem();
        slots[10] = new ItemCreator(Material.NETHERRACK).setName("§6≡ §eNether chunck loaded : "+ RuleBoolean.NETHERLOAD.isEnabled()).setAmount(RuleBoolean.NETHERLOAD.isEnabled() ? 1 : 0).getItem();
        slots[11] = new ItemCreator(Material.APPLE).setName("§6≡ §eApple rate : " + RuleInteger.APPLERATE.getValue()).getItem();
        slots[12] = new ItemCreator(Material.FLINT_AND_STEEL).setName("§6≡ §eFlint rate : " + RuleInteger.FLINTRATE.getValue()).getItem();
        slots[13] = new ItemCreator(Material.BARRIER).setName("§6≡ §eBorder diameter : " + (int) Bukkit.getWorld("world").getWorldBorder().getSize()).getItem();
        slots[14] = new ItemCreator(Material.BEDROCK).setName("§6≡ §eFinal Border Size : " + RuleInteger.FINALBORDERSIZE.getValue()).getItem();
        slots[15] = new ItemCreator(Material.LAVA_BUCKET).setName("§6≡ §eLava bucket : " + RuleBoolean.LAVABUCKET.isEnabled()).setAmount(RuleBoolean.LAVABUCKET.isEnabled() ? 1 : 0).getItem();
        slots[16] = new ItemCreator(Material.FLINT_AND_STEEL).setName("§6≡ §eFlint and steel : " + RuleBoolean.FLINTANDSTEEL.isEnabled()).setAmount(RuleBoolean.FLINTANDSTEEL.isEnabled() ? 1 : 0).getItem();
        slots[17] = new ItemCreator(Material.NETHERRACK).setName("§6≡ §eItem burn : " + RuleBoolean.ITEMBURN.isEnabled()).setAmount(RuleBoolean.ITEMBURN.isEnabled() ? 1 : 0).getItem();

        slots[22] = new ItemCreator(Material.ARROW).setName("§6≡ §eBack").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9) {
            if (!RuleBoolean.WORLDLOAD.isEnabled()) {
                RuleBoolean.WORLDLOAD.setEnabled(true);
                new LoadingChunkTask(Bukkit.getWorld("world"), (int) (Bukkit.getWorld("world").getWorldBorder().getSize() / 2));
            }
        }
        if (slot == 10){
            if (!RuleBoolean.NETHERLOAD.isEnabled()) {
                RuleBoolean.NETHERLOAD.setEnabled(true);
                new LoadingChunkTask(Bukkit.getWorld("world_nether"), (int) (Bukkit.getWorld("world_nether").getWorldBorder().getSize() / 2));
            }
        }
        if (type.isLeftClick()){
            if (slot == 11){
                if (RuleInteger.APPLERATE.getValue() != 0)
                RuleInteger.APPLERATE.setValue(RuleInteger.APPLERATE.getValue() - 1);
            }
            if (slot == 12){
                if (RuleInteger.FLINTRATE.getValue() != 0)
                    RuleInteger.FLINTRATE.setValue(RuleInteger.FLINTRATE.getValue() - 25);
            }
            if (slot == 13){
                int i = (int) Bukkit.getWorld("world").getWorldBorder().getSize();
                if (i > 200)
                    Bukkit.getWorld("world").getWorldBorder().setSize(i - 100);
            }
            if (slot == 14){
                if (RuleInteger.FINALBORDERSIZE.getValue() != 50)
                    RuleInteger.FINALBORDERSIZE.setValue(RuleInteger.FINALBORDERSIZE.getValue() - 25);
            }
        }
        if (type.isRightClick()){
            if (slot == 11) {
                if (RuleInteger.APPLERATE.getValue() != 10)
                RuleInteger.APPLERATE.setValue(RuleInteger.APPLERATE.getValue() + 1);
            }
            if (slot == 12) {
                if (RuleInteger.FLINTRATE.getValue() != 100)
                RuleInteger.FLINTRATE.setValue(RuleInteger.FLINTRATE.getValue() + 25);
            }
            if (slot == 13) {
                int i = (int) Bukkit.getWorld("world").getWorldBorder().getSize();
                if (i != 3000)
                Bukkit.getWorld("world").getWorldBorder().setSize(i + 100);
            }
            if (slot == 14) {
                if (RuleInteger.FINALBORDERSIZE.getValue() != 500)
                RuleInteger.FINALBORDERSIZE.setValue(RuleInteger.FINALBORDERSIZE.getValue() + 25);
            }
        }
        if (slot == 15)
            RuleBoolean.LAVABUCKET.setEnabled(!RuleBoolean.LAVABUCKET.isEnabled());
        if (slot == 16)
            RuleBoolean.FLINTANDSTEEL.setEnabled(!RuleBoolean.FLINTANDSTEEL.isEnabled());
        if (slot == 17)
            RuleBoolean.ITEMBURN.setEnabled(!RuleBoolean.ITEMBURN.isEnabled());

        Main.getInstance().getGui().open(player, World.class);
        if (slot == 22){
            Main.getInstance().getGui().open(player, Configuration.class);
        }


    }

    @Override
    public int getRows() {
        return 3;
    }
}
