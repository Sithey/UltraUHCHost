package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.List;

public class RedArrows extends CustomScenario {
    @Override
    public String getName() {
        return "Red Arrows";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.REDARROWS.getDescription().split(" ")){
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
        return new ItemCreator(Material.WOOL).setName(getName() + " : " + Scenario.REDARROWS.isEnabled()).setAmount(Scenario.REDARROWS.isEnabled() ? 1 : 0).setLores(textes).getItem();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void scatterPlayer(Player player) {

    }

    @Override
    public void onPvP() {

    }

    @Override
    public void onMeetup() {

    }

    @Override
    public void onDeath(Player player, Player killer) {
        createRedArrow(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0, 30, 0));
    }

    @Override
    public String getPath() {
        return "redarrows";
    }

    private void createRedArrow(Location loc) {
        changeRedWool(loc.getBlock());

        int x = loc.getBlockX(); int y = loc.getBlockY();
        int z = loc.getBlockZ(); World w = loc.getWorld();

        changeRedWool(new Location(w, x, y + 1, z).getBlock());
        changeRedWool(new Location(w, x, y + 1, z+1).getBlock());
        changeRedWool(new Location(w, x, y + 1, z-1).getBlock());

        for(int i = 0; i < 18; i++) {
            changeRedWool(new Location(w, x, y + 2 + i, z).getBlock());
            changeRedWool(new Location(w, x, y + 2 + i, z+1).getBlock());
            changeRedWool(new Location(w, x, y + 2 + i, z+2).getBlock());
            changeRedWool(new Location(w, x, y + 2 + i, z-1).getBlock());
            changeRedWool(new Location(w, x, y + 2 + i, z-2).getBlock());
        }

        changeRedWool(new Location(w, x, y + 3, z+3).getBlock());
        changeRedWool(new Location(w, x, y + 3, z-3).getBlock());

        changeRedWool(new Location(w, x, y + 4, z+3).getBlock());
        changeRedWool(new Location(w, x, y + 4, z-3).getBlock());
        changeRedWool(new Location(w, x, y + 4, z+4).getBlock());
        changeRedWool(new Location(w, x, y + 4, z-4).getBlock());
    }

    private static void changeRedWool(Block block) {
        block.setType(Material.WOOL);

        BlockState state = block.getState();
        state.setData(new Wool(DyeColor.RED));
        state.update();
    }
}
