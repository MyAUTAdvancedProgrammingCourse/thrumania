package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author amin
 * @version 1.0.0
 */
public class IronMine extends Mine {

    private Image[] ironMineImages;
    private int ironMineAvailabeIron;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public IronMine(int x, int y) {
        super(x, y);
        loadResoure();
        ironMineAvailabeIron = 20000;
        setToughness(100);
        setCurrentImage(ironMineImages[0]);
    }

    /**
     * loading images of iron mine that first element of array is incompleted iron mine and the last one is completed iron mine.
     */
    @Override
    public void loadResoure() {
        ironMineImages = new Image[1];
        try {
            ironMineImages[0] = ImageIO.read(new File(ResourcePath.itemImagePath + "mine\\ironmine\\ironmine.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
