package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.*;
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
public class Port extends Place {

    private Image[] portImages;
    private int numberOfWorkers;
    private int toughness;

    public Port(int x, int y) {
        super(x, y);
        loadResoure();
        portImages = new Image[3];
        toughness = Place.RATE_OF_BUILDING_PORT * numberOfWorkers;
    }

    /**
     * loading images of ports that first element of array is incompleted port and the last one is completed port.
     */
    @Override
    public void loadResoure() {
        try {
            portImages[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"port\\port_1.png"));
            portImages[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"port\\port_2.png"));
            portImages[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"port\\port_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}