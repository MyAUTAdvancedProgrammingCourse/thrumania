package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author saman
 * @version 1.0.0
 */
public class SoldierAttackEvent extends Event{
    public SoldierAttackEvent(GameObject sender) {
        super(sender);
    }
}
