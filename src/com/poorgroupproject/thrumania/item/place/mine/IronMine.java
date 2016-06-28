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
    private int toughness; // the toughness of place and the rang is 0 to 100

    /**
     * Constructor
     * @param x
     * @param y
     */
    public IronMine(int x, int y) {
        super(x, y);
        ironMineImages = new Image[4];
        ironMineAvailabeIron = 20000;
        toughness = 100;
    }

    /**
     * loading images of iron mine that first element of array is incompleted iron mine and the last one is completed iron mine.
     */
    @Override
    public void loadResoure() {
        try {
            ironMineImages[0]= ImageIO.read(new File(ResourcePath.itemImagePath + "mine\\ironMine\\ironMine_1.png"));
            ironMineImages[1]= ImageIO.read(new File(ResourcePath.itemImagePath + "mine\\ironMine\\ironMine_2.png"));
            ironMineImages[2]= ImageIO.read(new File(ResourcePath.itemImagePath + "mine\\ironMine\\ironMine_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
