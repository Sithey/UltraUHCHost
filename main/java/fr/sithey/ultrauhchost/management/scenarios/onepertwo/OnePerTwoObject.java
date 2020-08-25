package fr.sithey.ultrauhchost.management.scenarios.onepertwo;

import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class OnePerTwoObject {

    private static List<OnePerTwoObject> ores = new ArrayList<>();
    public static OnePerTwoObject getObject(UPlayer player){
        for (OnePerTwoObject ores : ores){
            if (ores.player.equals(player))
                return ores;
        }
        return null;
    }
    private UPlayer player;
    private List<Material> mat;
    public OnePerTwoObject(UPlayer player){
        this.player = player;
        mat = new ArrayList<>();
        ores.add(this);
    }

    public List<Material> getMat() {
        return mat;
    }
}
