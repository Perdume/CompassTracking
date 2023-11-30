package org.perdume.compasstracking;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class TrackManager {

    HashMap<Player, Tracking> trackingHashMap = new HashMap<>();

    public Tracking getTrack(Player p){
        if(trackingHashMap.get(p) == null){
            Tracking tr = new Tracking(p);
            trackingHashMap.put(p, tr);
            return tr;
        }
        else{
            return trackingHashMap.get(p);
        }
    }
}
