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
    private int toughness; // the toughness of place and the range is 0 to 100
    private Queue<Human> humanQueue;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Port(int x, int y) {
        super(x, y);
        humanQueue = new LinkedList<>();

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
        portImages = new Image[3];
        try {
            portImages[0]= ImageIO.read(new File(ResourcePath.itemImagePath + "port/port_1.png"));
            portImages[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"port/port_2.png"));
            portImages[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"port/port_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }


    @Override
    public void processEvent(Event event) {
        if (event instanceof ConstructPlaceEvent && getToughness() <= 100) contruct();
        else if(event instanceof DestroyPlaceEvent && getToughness() >= 0) destroy();
    }

    /**
     * For each sent image, increase the toughness mount.
     */
    private void contruct(){
        setToughness(getToughness() + 2);
        imageChooser(getToughness());
    }

    /**
     * For each sent event, reduce the toughness amount.
     */
    private void destroy(){
        setToughness(getToughness() - 2);
        imageChooser(getToughness());
    }

    /**
     * Choose image for port for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    public void imageChooser(int toughness){
        if(toughness < 33) setCurrentImage(portImages[0]);
        else if(toughness > 33 && toughness < 66) setCurrentImage(portImages[1]);
        else if(toughness > 66 && toughness < 99) setCurrentImage(portImages[2]);
        else{ setCurrentImage(portImages[3]);}
    }

}