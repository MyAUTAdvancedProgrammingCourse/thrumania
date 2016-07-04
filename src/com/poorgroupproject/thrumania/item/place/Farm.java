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
        loadResoure();
        farmAvailabeFood = 8000;
        setToughness(0);
    }

    /**
     * loading images of farms that first element of array is incompleted farm and the last one is completed farm.
     */
    @Override
    public void loadResoure() {
        farmImage = new Image[4];
        try {
            farmImage[0]= ImageIO.read(new File(ResourcePath.itemImagePath +"farm\\farm_1.png"));
            farmImage[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"farm\\farm_2.png"));
            farmImage[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"farm\\farm_3.png"));
            farmImage[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"farm\\farm_4.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(com.poorgroupproject.thrumania.events.Event event) {
        if (event instanceof ConstructPlaceEvent && getToughness() <= 40) contruct();
        else if(event instanceof DestroyPlaceEvent && getToughness() >= 0) destroy();
    }

    @Override
    public void tik() {

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
     * Choose image for Farm for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    private void imageChooser(int toughness){
        if(toughness < 13) setCurrentImage(farmImage[0]);
        else if (toughness > 13 && toughness < 26) setCurrentImage(farmImage[1]);
        else if (toughness > 26 && toughness < 39) setCurrentImage(farmImage[2]);
        else setCurrentImage(farmImage[3]);
    }


}
