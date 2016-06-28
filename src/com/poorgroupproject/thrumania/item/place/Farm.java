package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.ConstructPlaceEvent;
import com.poorgroupproject.thrumania.events.DestroyPlaceEvent;
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
public class Farm extends Place {

    private Image[] farmImage;
    private int farmAvailabeFood;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Farm(int x, int y) {
        super(x, y);
        farmImage = new Image[4];
        farmAvailabeFood = 8000;
    }

    /**
     * loading images of farms that first element of array is incompleted farm and the last one is completed farm.
     */
    @Override
    public void loadResoure() {
        try {
            farmImage[0]= ImageIO.read(new File(ResourcePath.itemImagePath +"farm\\farm_1.png"));
            farmImage[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"farm\\farm_2.png"));
            farmImage[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"farm\\farm_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(com.poorgroupproject.thrumania.events.Event event) {
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
     * Choose image for farm for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    private void imageChooser(int toughness){
        if(toughness < 33) setCurrentImage(farmImage[0]);
        else if (toughness > 33 && toughness < 66) {
            setCurrentImage(farmImage[1]);
        }
        else if (toughness > 66 && toughness < 99) {
            setCurrentImage(farmImage[2]);
        }
        else
            setCurrentImage(farmImage[3]);
    }


}
