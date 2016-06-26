package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Human;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author amin
 * @version 1.0.0
 */
public class Port extends Place {

    private Image[] portImages;
    private int toughness; // the toughness of place and the rang is 0 to 100
    private Queue<Human> humanQueue;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Port(int x, int y) {
        super(x, y);
        loadResoure();
        humanQueue = new LinkedList<>();
        portImages = new Image[3];
        toughness = Place.RATE_OF_BUILDING_PORT * numberOfWorkers;
    }

    /**
     * Adding human to queue
     * @param humanQueue
     * @param human
     */
    public void addingToHumanQueue(Queue<Human> humanQueue, Human human){
        humanQueue.add(human);
    }

    /**
     * Removing human from queue
     * @param humanQueue
     * @return
     */
    public Human removingFromHumanQueue(Queue<Human> humanQueue){
        return humanQueue.poll();
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