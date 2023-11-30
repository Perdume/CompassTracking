package org.perdume.compasstracking;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CompassTracking extends JavaPlugin {

    TrackManager trm = new TrackManager();

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EvH(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
