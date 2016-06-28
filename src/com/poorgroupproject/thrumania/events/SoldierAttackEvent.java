package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.sun.javafx.geom.Rectangle;

import javax.swing.*;

/**
 * @author saman
 * @version 1.0.0
 */
public class SoldierAttackEvent extends Event{
    GameObject reciver;
    public SoldierAttackEvent(GameObject sender,GameObject reciver) {
        super(sender);
        this.reciver = reciver;
    }
}
