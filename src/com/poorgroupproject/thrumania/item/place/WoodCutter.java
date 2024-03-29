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
public class WoodCutter extends Place {

    private Image[] woodCutterImage;
    /**
     * Constructor
     * @param x
     * @param y
     */
    public WoodCutter(int x, int y) {
        super(x, y);
        loadResoure();
        setToughness(0);
    }

    /**
     * loading images of woodcutter that first element of array is incompleted woodcutter and the last one is completed woodcutter.
     */
    @Override
    public void loadResoure() {
        woodCutterImage = new Image[4];
        try {
            woodCutterImage[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_1.png"));
            woodCutterImage[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_2.png"));
            woodCutterImage[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_3.png"));
            woodCutterImage[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_4.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {
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
     * Choose image for woodcutter for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    private void imageChooser(int toughness){
        if(toughness < 13) setCurrentImage(woodCutterImage[0]);
        else if(toughness >= 13 && toughness < 26) setCurrentImage(woodCutterImage[1]);
        else if(toughness >= 26 && toughness < 39) setCurrentImage(woodCutterImage[2]);
        else setCurrentImage(woodCutterImage[3]);
    }

}
