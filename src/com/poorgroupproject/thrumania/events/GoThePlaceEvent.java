package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.Path;

/**
 * Created by Saman A.Mirhoseini on 29/06/2016.
 */
public class GoThePlaceEvent extends Event {
    public int targetX;
    public int targetY;
    public GoThePlaceEvent(GameObject sender,int x,int y) {
        super(sender);
        targetX = x;
        targetY = y;
 = }
    public GoThePlaceEvent(GameObject sender, Pair p) {
        super(sender);
        targetX = p.getX();
        targetY = p.getY();
    }
}
