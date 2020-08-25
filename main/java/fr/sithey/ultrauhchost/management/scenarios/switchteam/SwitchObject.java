package fr.sithey.ultrauhchost.management.scenarios.switchteam;

import fr.sithey.ultrauhchost.management.object.UPlayer;
import fr.sithey.ultrauhchost.management.object.UTeam;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SwitchObject {


    private UTeam team;
    private UPlayer player;
    private Location location;
    public SwitchObject(UTeam uTeam, UPlayer player){
        this.team = uTeam;
        this.player = player;
        this.location = player.getPlayer().getLocation();

        list.add(this);
    }

    public static List<SwitchObject> list = new ArrayList<>();

    public static void switchteam(){
        list.clear();
        for (UTeam uTeam : UTeam.teams){
            if (!uTeam.getAlive().isEmpty()){
                new SwitchObject(uTeam, uTeam.getAlive().get(new Random().nextInt(uTeam.getAlive().size())));
            }
        }
        Collections.shuffle(list, new Random(new Random().nextInt(10)));
        for (int i = 0; i < list.size() - 1; i++){
            changeTeam(list.get(i), list.get(i+1));
        }
        changeTeam(list.get(list.size() - 1), list.get(0));
    }

    private static void changeTeam(SwitchObject before, SwitchObject after){
        before.player.getPlayer().teleport(after.location);
        UTeam.addPlayerBYPASS(before.player.getPlayer(), after.team);
    }
}
