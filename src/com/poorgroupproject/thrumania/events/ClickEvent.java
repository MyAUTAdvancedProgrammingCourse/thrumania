package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class ClickEvent extends Event {
    public ClickEvent(GameObject sender) {
        super(sender);
    }
}
