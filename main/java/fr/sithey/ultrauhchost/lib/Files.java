package fr.sithey.ultrauhchost.lib;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.management.object.UPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {
    private String filename;
    public Files(String filename){
        this.filename = filename;

        create();
    }

    public File getFile() {
        return new File(Main.getInstance().getDataFolder(), filename);
    }

    public FileConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(getFile());
    }

    public void save(FileConfiguration config){
        try {
            config.save(getFile());
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cERROR TO SAVE " + getConfig() + " PLEASE REPORT THIS ON MY TWITTER @SitheyMC");
            e.printStackTrace();
        }
    }

    public void create(){
        if (!Main.getInstance().getDataFolder().exists())
            Main.getInstance().getDataFolder().mkdir();

        try {
            getFile().createNewFile();
            FileConfiguration config = getConfig();
            config.options().copyDefaults(true);
            save(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addValue(String path, Object value){
        FileConfiguration config = getConfig();
        if (config.get(path) != null){
            return;
        }
        config.set(path, value);
        save(config);
    }

    public void setValue(String path, Object value){
        FileConfiguration config = getConfig();
        config.set(path, value);
        save(config);
    }

    public String getValue(String path){
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString(path)).replace("/n", "\n");
    }
}
