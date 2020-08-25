package fr.sithey.ultrauhchost.gui.host;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomInventory;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Statut;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleBoolean;
import fr.sithey.ultrauhchost.management.enumeration.rules.RuleInteger;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import fr.sithey.ultrauhchost.management.task.Scatter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Configuration implements CustomInventory {
    private List<Player> hoststart = new ArrayList<>();
    @Override
    public String getName() {
        return "§7● §cConfiguration";
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
        slots[9] = new ItemCreator(Material.SUGAR).setName("§6≡ §eTimer").getItem();
        slots[19] = new ItemCreator(Material.GRASS).setName("§6≡ §eWorld").getItem();
        slots[11] = new ItemCreator(Material.OBSIDIAN).setName("§6≡ §eNether").getItem();
        slots[21] = new ItemCreator(Material.NAME_TAG).setName("§6≡ §eServer").getItem();
        slots[13] = new ItemCreator(Material.CHEST).setName("§6≡ §eInventory").getItem();
        slots[23] = new ItemCreator(Material.BANNER).setName("§6≡ §eTeams").getItem();
        slots[15] = new ItemCreator(Material.CARROT_ITEM).setName("§6≡ §eItems").getItem();
        slots[25] = new ItemCreator(Material.DIAMOND).setName("§6≡ §eRules").getItem();
        slots[17] = new ItemCreator(Material.PAPER).setName("§6≡ §eScenarios").getItem();

        slots[40] = new ItemCreator(Material.EMERALD_BLOCK).setName("§6≡ §eStart").addLore("§7■ §fdouble click to start").getItem();
        return () -> slots;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, ClickType type) {
        if (slot == 9)
            Main.getInstance().getGui().open(player, Timer.class);

        if (slot == 11)
            Main.getInstance().getGui().open(player, Nether.class);

        if (slot == 13)
            Main.getInstance().getGui().open(player, fr.sithey.ultrauhchost.gui.host.Inventory.class);

        if (slot == 15)
            Main.getInstance().getGui().open(player, Items.class);

        if (slot == 17)
            Main.getInstance().getGui().open(player, Scenarios1.class);

        if (slot == 25)
            Main.getInstance().getGui().open(player, Rules.class);

        if (slot == 19)
            Main.getInstance().getGui().open(player, World.class);

        if (slot == 21)
            Main.getInstance().getGui().open(player, Server.class);

        if (slot == 23)
            Main.getInstance().getGui().open(player, Teams.class);

        if (slot == 40){
            if (hoststart.contains(player)) {
                player.closeInventory();
                Main.getInstance().getUHC().setStatut(Statut.SCATTER);
                if (RuleBoolean.HOSTPLAY.isEnabled()){
                    if (UPlayer.getHost != null){
                        UPlayer.getHost.setPlaying(true);
                    }
                }else{
                    if (UPlayer.getHost != null){
                        UPlayer.getHost.setPlaying(false);
                        if (UPlayer.getHost.getPlayer() != null){
                            UPlayer.getHost.getPlayer().setGameMode(GameMode.SPECTATOR);
                        }
                    }
                }
                Bukkit.getServer().setWhitelist(true);
                if (RuleBoolean.RANDOMTEAM.isEnabled()){
                    int i = 0;
                    if (RuleInteger.TEAMSIZE.getValue() > 1){
                        List<UPlayer> cache = new ArrayList<>(UPlayer.getAlivePlayers());
                        UTeam team = null;
                        for (UPlayer c : cache){
                            i++;
                            if (i == 1){
                                team = UTeam.createTeam(c);
                            }
                            UTeam.addPlayerBYPASS(c.getPlayer(), team);
                            if (i == RuleInteger.TEAMSIZE.getValue()){
                                i = 0;
                            }
                        }
                    }
                }
                if (RuleBoolean.CRAFTGOLDENHEAD.isEnabled()) {
                    ShapedRecipe goldenhead = new ShapedRecipe(new ItemCreator(Material.GOLDEN_APPLE).setName(ChatColor.GOLD + "Golden Head").getItem());
                    goldenhead.shape("###", "#%#", "###");
                    goldenhead.setIngredient('#', Material.GOLD_INGOT);
                    goldenhead.setIngredient('%', Material.SKULL_ITEM, 3);
                    Main.getInstance().getServer().addRecipe(goldenhead);
                }
                if (RuleBoolean.WOOLTOSTRING.isEnabled()) {
                    ShapedRecipe r1 = new ShapedRecipe(new ItemStack(Material.STRING, 1));
                    r1.shape("WW ", "WW ", "   ");
                    r1.setIngredient('W', Material.WOOL);
                    Bukkit.getServer().addRecipe(r1);

                    ShapedRecipe r2 = new ShapedRecipe(new ItemStack(Material.STRING, 1));
                    r2.shape(" WW", " WW", "   ");
                    r2.setIngredient('W', Material.WOOL);
                    Bukkit.getServer().addRecipe(r2);

                    ShapedRecipe r3 = new ShapedRecipe(new ItemStack(Material.STRING, 1));
                    r3.shape("   ", "WW ", "WW ");
                    r3.setIngredient('W', Material.WOOL);
                    Bukkit.getServer().addRecipe(r3);

                    ShapedRecipe r4 = new ShapedRecipe(new ItemStack(Material.STRING, 1));
                    r4.shape("   ", " WW", " WW");
                    r4.setIngredient('W', Material.WOOL);
                    Bukkit.getServer().addRecipe(r4);
                }
                new Scatter(((int) Bukkit.getWorld("world").getWorldBorder().getSize()) / 2, UPlayer.getAlivePlayers()).runTaskTimer(Main.getInstance(), 0, 5);
                Bukkit.broadcastMessage(Message.SCATTERSTARTING.getMessage().replace("<online>", UPlayer.getAlivePlayers().size() + ""));
            }else{
                hoststart.add(player);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        hoststart.remove(player);
                    }
                }.runTaskLater(Main.getInstance(), 10);
            }
        }
    }

    @Override
    public int getRows() {
        return 5;
    }
}
