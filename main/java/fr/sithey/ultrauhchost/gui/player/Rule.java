package fr.sithey.ultrauhchost.gui.player;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.Time;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.task.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Rule implements CustomInventory {
    private List<Player> hoststart = new ArrayList<>();
    @Override
    public String getName() {
        return "§7● §cInformation about rules";
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
        slots[9] = new ItemCreator(Material.SUGAR).setName("§6≡ §eTimer")
                .addLore("")
                .addLore("§7■ §fFinal heal : §e" + Time.FINALHEAL.getString())
                .addLore("§7■ §fPvP : §e" + Time.PVP.getString())
                .addLore("§7■ §fMeetup : §e" + Time.MEETUP.getString())
                .addLore("")
                .getItem();
        slots[10] = new ItemCreator(Material.GRASS).setName("§6≡ §eWorld")
                .addLore("")
                .addLore("§7■ §fApple rate : §e" + RuleInteger.APPLERATE.getValue() + "%")
                .addLore("§7■ §fFlint rate : §e" + RuleInteger.FLINTRATE.getValue() + "%")
                .addLore("§7■ §fBorder diameter : §e" + (int) Bukkit.getWorld("world").getWorldBorder().getSize())
                .addLore("§7■ §fFinal border diameter : §e" + RuleInteger.FINALBORDERSIZE.getValue())
                .addLore("§7■ §fLava bucket : §e" + (RuleBoolean.LAVABUCKET.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fFlint and steel : §e" + (RuleBoolean.FLINTANDSTEEL.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fItem burn : §e" + (RuleBoolean.ITEMBURN.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("")
                .getItem();
        slots[11] = new ItemCreator(Material.OBSIDIAN)
                .addLore("")
                .addLore("§7■ §fNether : §e" + (RuleBoolean.NETHER.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fPotion : §e" + (RuleBoolean.POTION.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fStrength : §e" + (RuleBoolean.STRENGTH.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fSpeed : §e" + (RuleBoolean.SPEED.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fPoison : §e" + (RuleBoolean.POISON.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fInvisibility : §e" + (RuleBoolean.INVISIBILITY.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fHeal : §e" + (RuleBoolean.HEAL.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fRegen : §e" + (RuleBoolean.REGEN.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fLevel II : §e" + (RuleBoolean.LEVEL2.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("").setName("§6≡ §eNether").getItem();
        slots[15] = new ItemCreator(Material.BANNER).setName("§6≡ §eTeams")
                .addLore("")
                .addLore("§7■ §fTeam size : §e" + (RuleInteger.TEAMSIZE.getValue() == 1 ? "FFA" : "cTo" + RuleInteger.TEAMSIZE.getValue()))
                .addLore("§7■ §fFriendly fire : " + (RuleBoolean.FRIENDLYFIRE.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fRandom Team : " + (RuleBoolean.RANDOMTEAM.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("").getItem();
        slots[17] = new ItemCreator(Material.CARROT_ITEM).setName("§6≡ §eItems")
                .addLore("")
                .addLore("§7■ §fNotch Apple : §e" + (RuleBoolean.NOTCHAPPLE.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fHead : §e" + (RuleBoolean.HEAD.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fGolden head to kill : §e" + (RuleBoolean.GHEAD.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fGApple to kill : §e" + (RuleBoolean.GAPPLE.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fFire aspect : §e" + (RuleBoolean.FIRE_ASPECT.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fFlame : §e" + (RuleBoolean.FLAME.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fCraft Golden Head : §e" + (RuleBoolean.CRAFTGOLDENHEAD.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fWool Craft to String : §e" + (RuleBoolean.WOOLTOSTRING.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("").getItem();
        slots[16] = new ItemCreator(Material.DIAMOND).setName("§6≡ §eRules")
                .addLore("")
                .addLore("§7■ §fRoller caoster : §e" + (RuleBoolean.ROLLERCAOSTER.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fStrip Mining : §e" + (RuleBoolean.STIPMINING.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fPoke holling : §e" + (RuleBoolean.POKEHOLLING.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fDig down at meetup : §e" + (RuleBoolean.DIGDOWNATMEETUP.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fStalk : §e" + (RuleBoolean.STALK.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("§7■ §fTrap : §e" + (RuleBoolean.TRAP.isEnabled() ? "§2✔" : "§4✖"))
                .addLore("").getItem();
        slots[13] = new ItemCreator(Material.PAPER).setName("§6≡ §eScenarios").getItem();

        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 13)
            player.performCommand("sc");
    }

    @Override
    public int getRows() {
        return 3;
    }
}
