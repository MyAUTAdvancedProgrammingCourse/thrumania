package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.pathfinder.Pair;

/**
 * Created by Ahmad on 7/3/2016.
 */
public class GoAndCollectResourceEvent extends Event {
    public Pair target;
    public GoAndCollectResourceEvent(GameObject sender,Pair p) {
        super(sender);
        this.target = p;
    }
}
