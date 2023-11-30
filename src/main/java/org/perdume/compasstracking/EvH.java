package org.perdume.compasstracking;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EvH implements Listener {
    private CompassTracking main = CompassTracking.getPlugin(CompassTracking.class);
    @EventHandler
    public void getclick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS){
                GUI gui = new GUI(e.getPlayer());
                Bukkit.getServer().getPluginManager().registerEvents(gui, main);
                gui.openInventory();
            }
        }
    }
}
