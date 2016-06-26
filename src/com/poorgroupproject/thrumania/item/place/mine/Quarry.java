package com.poorgroupproject.thrumania.item.place.mine;

import java.awt.*;

/**
 * @author amin
 * @version 1.0.0
 */
public class Quarry extends Mine {

    private int availabeResource;
    private int toughness;
    private Image[] quarry;

    public Quarry(int x, int y) {
        super(x,y);
        availabeResource = 0;
        toughness = 100;
        quarry = new Image[4];
    }
}
