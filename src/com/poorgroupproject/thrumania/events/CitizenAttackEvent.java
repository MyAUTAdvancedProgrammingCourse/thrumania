package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * Created by ahmad on 6/22/16.
 */
public class CitizenAttackEvent extends Event {
    GameObject reciever;
    public CitizenAttackEvent(GameObject sender,GameObject reciever) {
        super(sender);
        this.reciever = reciever;
    }
}
