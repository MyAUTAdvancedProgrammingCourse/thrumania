package com.poorgroupproject.thrumania.events;


import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;

/**
 * Created by Saman A.Mirhoseini on 29/06/2016.
 */
public class GoAndAttack extends Event {
    Human target;
    public GoAndAttack(GameObject sender,Human target) {
        super(sender);
        this.target = target;
    }
}
