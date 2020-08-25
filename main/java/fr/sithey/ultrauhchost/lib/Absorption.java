package fr.sithey.ultrauhchost.lib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class Absorption {
    private Player player;
    public Absorption(Player player){
        this.player = player;
    }

    public float getAbso() {
        try {
            Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
            Object converted = craftPlayer.cast(player);
            Method handle = converted.getClass().getMethod("getHandle", new Class[0]);
            Object entityPlayer = handle.invoke(converted, new Object[0]);
            Method abso = entityPlayer.getClass().getMethod("getAbsorptionHearts", new Class[0]);
            return (float) abso.invoke(entityPlayer, new Double[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    private String getServerVersion() {
        Pattern brand = Pattern.compile("(v|)[0-9][_.][0-9][_.][R0-9]*");

        String pkg = Bukkit.getServer().getClass().getPackage().getName();
        String version = pkg.substring(pkg.lastIndexOf('.') + 1);
        if (!brand.matcher(version).matches()) {
            version = "";
        }

        return version;
    }
}
