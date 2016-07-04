package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.place.Place;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author amin
 * @version 1.0.0
 */
public class GoldMine extends Mine {

    private Image[] goldMineImages;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public GoldMine(int x, int y) {
        super(x, y);
        loadResoure();
        setToughness(100);
        setCurrentImage(goldMineImages[0]);
    }

    /**
     * loading images of gold mine that first element of array is incompleted gold mine and the last one is completed gold mine.
     */
    @Override
    public void loadResoure() {
        goldMineImages = new Image[1];
        try {
            goldMineImages[0] = ImageIO.read(new File(ResourcePath.itemImagePath + "mine\\goldmine\\goldmine.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
