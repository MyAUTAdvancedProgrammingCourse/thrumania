package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;

import java.util.ArrayList;

/**
 * @author amin
 * @version 1.0.0
 */
public abstract class Place extends GameObject {
    private int toughness;

    /**
     * Constructor
     * @param x
     * @param y
     */
    protected Place(int x, int y) {
        super(x, y, 100,100);
    }

    protected int getToughness() {
        return toughness;
    }
    protected void setToughness(int toughness){
        this.toughness = toughness;
    }
}
