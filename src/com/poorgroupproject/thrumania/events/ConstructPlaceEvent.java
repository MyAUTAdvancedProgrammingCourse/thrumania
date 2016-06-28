package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author amin
 * @version 1.0.0
 */
public class ConstructPlaceEvent extends Event{
    public ConstructPlaceEvent(GameObject sender) {
        super(sender);
    }
}
