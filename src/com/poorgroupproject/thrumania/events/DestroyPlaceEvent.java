package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author amin
 * @version 1.0.0
 */
public class DestroyPlaceEvent extends Event {
    public DestroyPlaceEvent(GameObject sender) {
        super(sender);
    }
}
