package fr.sithey.ultrauhchost.management.task;

import fr.sithey.ultrauhchost.Main;
import fr.sithey.ultrauhchost.lib.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoadingChunkTask extends BukkitRunnable {

    private Double percent;
    @SuppressWarnings("unused")
    private Integer ancientPercent;
    private Double currentChunkLoad;
    private Double totalChunkToLoad;
    private Integer cx;
    private Integer cz;
    private Integer radius;
    private Boolean finished;

    private World world;

    public LoadingChunkTask(World world, Integer r) {

        r = Integer.valueOf(r.intValue() + 150);

        this.percent = Double.valueOf(0.0D);
        this.ancientPercent = Integer.valueOf(0);
        this.totalChunkToLoad = Double.valueOf(Math.pow(r.intValue(), 2.0D) / 64.0D);
        this.currentChunkLoad = Double.valueOf(0.0D);
        this.cx = Integer.valueOf(-r.intValue());
        this.cz = Integer.valueOf(-r.intValue());
        this.world = world;
        this.radius = r;
        this.finished = Boolean.valueOf(false);
        runTaskTimer(Main.getInstance(), 0, 5);
    }

    public void run() {

        for (int i = 0; (i < 30) && (!this.finished.booleanValue()); i++) {

            Location loc = new Location(this.world, this.cx.intValue(), 0.0D, this.cz.intValue());

            if(!loc.getChunk().isLoaded()) {
                loc.getWorld().loadChunk(loc.getChunk().getX(), loc.getChunk().getZ(), true);
            }

            this.cx = Integer.valueOf(this.cx.intValue() + 16);
            this.currentChunkLoad = Double.valueOf(this.currentChunkLoad.doubleValue() + 1.0D);

            if (this.cx.intValue() > this.radius.intValue()) {
                this.cx = Integer.valueOf(-this.radius.intValue());
                this.cz = Integer.valueOf(this.cz.intValue() + 16);

                if (this.cz > this.radius) {
                    this.currentChunkLoad = this.totalChunkToLoad;
                    this.finished = true;
                }
            }
        }

        this.percent = this.currentChunkLoad / this.totalChunkToLoad * 100.0D;

        {
            this.ancientPercent = Integer.valueOf(this.percent.intValue());
            percentage = percent.intValue();
            for (Player player : Bukkit.getOnlinePlayers()){
                Title.sendActionBar(player, ChatColor.RED + "Pregeneration : " + ChatColor.AQUA + percentage + ChatColor.RED + "%");
            }
        }
        if (this.finished){
            cancel();
        }
    }

    public static int percentage = 0;
}
