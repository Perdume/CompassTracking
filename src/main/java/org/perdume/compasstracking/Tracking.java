package org.perdume.compasstracking;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Tracking {
    private CompassTracking main = CompassTracking.getPlugin(CompassTracking.class);
    Player player;

    BukkitTask running = null;
    public Tracking(Player p){
        player = p;
    }
    public BukkitTask Track(Player target){
        running = new BukkitRunnable() {
            @Override
            public void run() {
                if(player.getLocation().distance(target.getLocation()) < 5){
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("추적종료(대상과 가까이 있음)"));
                    cancel();
                    return;
                }
                if(player.getWorld() != target.getWorld()){
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("추적종료(같은 월드가 아님)"));
                    cancel();
                    return;
                }
                player.setCompassTarget(target.getLocation());
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("추적중(" +target.getName()+"): "+ Math.round(player.getLocation().distance(target.getLocation()))));
            }
        }.runTaskTimer(main, 10, 10);
        return running;
    }

    public BukkitTask getTrack(){
        return running;
    }
    public void ResetTrack(){
        player.setCompassTarget(player.getLocation());
        running.cancel();
        running = null;
    }

}
