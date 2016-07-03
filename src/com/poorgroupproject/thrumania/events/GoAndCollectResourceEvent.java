package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * Created by Ahmad on 7/3/2016.
 */
public class GoAndCollectResourceEvent extends Event {
    public GoAndCollectResourceEvent(GameObject sender) {
        super(sender);
    }
}
