package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Amin Rashidbeigi
 * @version 1.0.0
 */
public class Port extends Place {

    private Image[] portImages;
    private int numberOfWorkers;

    public Port(int x, int y, int toughness) {
        super(x, y, toughness);
        portImages = new Image[3];
        toughness = ConstantPlaceVariables.RATE_OF_BUILDING_PORT * numberOfWorkers;
    }

    @Override
    public void loadResoure() {
        try {
            portImages[0]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"port\\port_1.png"));
            portImages[1]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"port\\port_2.png"));
            portImages[2]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"port\\port_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}