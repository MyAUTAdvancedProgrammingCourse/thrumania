package com.poorgroupproject.thrumania.item.place;

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
public class WoodCutter extends Place {

    private Image[] woodCutterImage;
    private int toughness; // the toughness of place and the rang is 0 to 100

    /**
     * Constructor
     * @param x
     * @param y
     */
    public WoodCutter(int x, int y) {
        super(x, y);
        toughness = 0;
        woodCutterImage = new Image[3];
    }


    /**
     * loading images of woodcutter that first element of array is incompleted woodcutter and the last one is completed woodcutter.
     */
    @Override
    public void loadResoure() {
        try {
            woodCutterImage[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_1.png"));
            woodCutterImage[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_2.png"));
            woodCutterImage[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
