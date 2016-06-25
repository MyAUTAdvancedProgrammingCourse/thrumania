package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * Created by ahmad on 6/22/16.
 */
public class CitizenAttackEvent extends Event {
    public CitizenAttackEvent(GameObject sender) {
        super(sender);
    }
}
