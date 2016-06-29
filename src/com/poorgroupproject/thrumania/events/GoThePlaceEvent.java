package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * Created by Saman A.Mirhoseini on 29/06/2016.
 */
public class GoThePlaceEvent extends Event {
    int targetX;
    int targetY;
    public GoThePlaceEvent(GameObject sender,int x,int y) {
        super(sender);
    }
}
