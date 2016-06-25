package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.item.place.Place;

/**
 * @author amin
 * @version 1.0.0
 */
public class Mine extends Place {

    private int availableResource;

    public Mine(int x, int y, int toughness, int availableResource) {
        super(x, y, toughness);
        this.availableResource = availableResource;
    }
}
