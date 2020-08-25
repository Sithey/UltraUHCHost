package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Message;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AchievementParanoia extends CustomScenario implements Listener {

    @Override
    public String getName() {
        return "Achievement Paranoia";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.ACHIEVEMENTPARANOIA.getDescription().split(" ")){
            newmsg.append(bout).append(" ");
            if(i == 8){
                textes.add(newmsg.toString());
                newmsg = new StringBuilder(ChatColor.GRAY + "");
                i = 0;
            }
            i++;
        }
        if(!newmsg.toString().equals(ChatColor.GRAY + "")){
            textes.add(newmsg.toString());
        }
        textes.add("");
        textes.add("§8§m--------------------------------------");
        return new ItemCreator(Material.PAPER).setName(getName() + " : " + Scenario.ACHIEVEMENTPARANOIA.isEnabled()).setAmount(Scenario.ACHIEVEMENTPARANOIA.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @Override
    public void scatterPlayer(Player player) {
        player.removeAchievement(Achievement.MINE_WOOD);
        player.awardAchievement(Achievement.OPEN_INVENTORY);
    }

    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {

    }

    @Override
    public void onDeath(Player player, Player killer) {

    }

    @Override
    public String getPath() {
        return "achievementparanoia";
    }

    private static final String FORMAT_STRING = Message.PREFIX.getMessage() + "§a%s§f has earned §e%s§f at %s.";

    @EventHandler
    public void on(final PlayerAchievementAwardedEvent event) {
        final Achievement ach = event.getAchievement();
        final Player player = event.getPlayer();
        Bukkit.broadcastMessage(String.format(FORMAT_STRING, player.getName(), achievementName(ach), locToString(player.getLocation())));
    }
    private static String locToString(final Location loc) {
        return loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ();
    }
    private static String achievementName(final Achievement ach) {
        switch (ach) {
            case ACQUIRE_IRON:
                return "Acquire Hardware";
            case BAKE_CAKE:
                return "The Lie";
            case BOOKCASE:
                return "Librarian";
            case BREED_COW:
                return "Repopulation";
            case BREW_POTION:
                return "Local Brewery";
            case BUILD_BETTER_PICKAXE:
                return "Getting an Upgrade";
            case BUILD_FURNACE:
                return "Hot Topic";
            case BUILD_HOE:
                return "Time to Farm";
            case BUILD_PICKAXE:
                return "Time to Mine";
            case BUILD_SWORD:
                return "Time to Strike";
            case BUILD_WORKBENCH:
                return "Benchmarking";
            case COOK_FISH:
                return "Delicious Fish";
            case DIAMONDS_TO_YOU:
                return "Diamonds to you";
            case ENCHANTMENTS:
                return "Enchanter";
            case END_PORTAL:
                return "The End?";
            case EXPLORE_ALL_BIOMES:
                return "Adventuring time";
            case FLY_PIG:
                return "When Pigs Fly";
            case FULL_BEACON:
                return "Beaconator";
            case GET_BLAZE_ROD:
                return "Into Fire";
            case GET_DIAMONDS:
                return "DIAMONDS";
            case GHAST_RETURN:
                return "Return to Sender";
            case KILL_COW:
                return "Cow Tipper";
            case KILL_ENEMY:
                return "Monster Hunter";
            case KILL_WITHER:
                return "The Beginning";
            case MAKE_BREAD:
                return "Bake Bread";
            case MINE_WOOD:
                return "Getting Wood";
            case NETHER_PORTAL:
                return "We Need to Go Deeper";
            case ON_A_RAIL:
                return "On A Rail";
            case OPEN_INVENTORY:
                return "Taking Inventory";
            case OVERKILL:
                return "Overkill";
            case OVERPOWERED:
                return "Overpowered";
            case SNIPE_SKELETON:
                return "Sniper Duel";
            case SPAWN_WITHER:
                return "The Beginning?";
            case THE_END:
                return "The End";
            default:
                // didn't find it, return a string in lower case of the enum name with out _'s
                return ach.name().toLowerCase().replaceAll("_", " ");
        }
    }

}
