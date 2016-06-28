package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author yahay
 * @version 1.0.0
 */
public class GoTargetEvent extends Event{
    int x,y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GoTargetEvent(GameObject sender , int x, int y) {
        super(sender);
        this.x=x;
        this.y=y;

    }
}
