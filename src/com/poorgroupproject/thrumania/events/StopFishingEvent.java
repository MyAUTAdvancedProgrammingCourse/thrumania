package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author yahya
 * @version 1.0.0
 */
public class StopFishingEvent extends Event {
    public StopFishingEvent(GameObject sender) {
        super(sender);
    }
}
