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
    private int numberOfWorkingHuman;
    static final int RATE_OF_BUILDING_PORT = 10;

    /**
     * Constructor
     * @param x
     * @param y
     */
    protected Place(int x, int y) {
        super(x, y, 100,100);
    }

    int getToughness() {
        return toughness;
    }

    public int getNumberOfWorkingHuman() {
        return numberOfWorkingHuman;
    }

}
