package fr.sithey.ultrauhchost.management.scenarios;

import fr.sithey.ultrauhchost.lib.CustomScenario;
import fr.sithey.ultrauhchost.lib.ItemCreator;
import fr.sithey.ultrauhchost.management.enumeration.Scenario;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Webcage extends CustomScenario {
    @Override
    public String getName() {
        return "Webcage";
    }


    @Override
    public ItemStack getItem() {
        List<String> textes = new ArrayList<>();
        textes.add("§8§m--------------------------------------");
        textes.add("");
        StringBuilder newmsg = new StringBuilder(ChatColor.GRAY + "");
        int i = 0;
        for(String bout : Scenario.WEBCAGE.getDescription().split(" ")){
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
        return new ItemCreator(Material.WEB).setName(getName() + " : " + Scenario.WEBCAGE.isEnabled()).setAmount(Scenario.WEBCAGE.isEnabled() ? 1 : 0).setLores(textes).getItem();
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
        for(Location block : getSphere(player.getLocation())){

            Block b = block.getBlock();
            if(b.getType() == Material.AIR || b.getType() == Material.LONG_GRASS){
                b.setType(Material.WEB);
            }

        }

    }

    @Override
    public String getPath() {
        return "webcage";
    }
    private List<Location> getSphere(Location centerBlock){

        List<Location> circleBlocks = new ArrayList<>();

        int bX = centerBlock.getBlockX();
        int bY = centerBlock.getBlockY();
        int bZ = centerBlock.getBlockZ();

        for(int x = bX - 5; x <= bX + 5; x++){
            for(int y = bY - 5; y <= bY + 5; y++){
                for(int z = bZ - 5; z <= bZ + 5; z++){

                    double distance = ((bX - x) * (bX - x) + ((bZ - z) * (bZ - z)) + ((bY - y) * (bY - y)));

                    if(distance < 5 * 5 && !(true && distance < ((5 -1) * (5 -1)))){

                        Location block = new Location(centerBlock.getWorld(), x, y, z);
                        circleBlocks.add(block);
                    }
                }
            }
        }
        return circleBlocks;
    }

}
