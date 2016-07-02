package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.pathfinder.Pair;

/**
 * Created by Saman A.Mirhoseini on 29/06/2016.
 */
public class GoThePlaceEvent extends Event {
    public int targetX;
    public int targetY;
    public GoThePlaceEvent(GameObject sender,int x,int y) {
        super(sender);
    }

    public GoThePlaceEvent(GameObject sender, Pair pair){
        super(sender);
        targetX = pair.getX();
        targetY = pair.getY();
    }
}
