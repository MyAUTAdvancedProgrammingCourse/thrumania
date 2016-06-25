package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Event {
    private GameObject sender;

    public Event(GameObject sender){
        this.sender = sender;
    }

    public GameObject getSender(){
        return sender;
    }
}
