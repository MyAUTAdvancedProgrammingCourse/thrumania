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
    private int toughness; // the toughness of place and the rang is 0 to 100

    /**
     * Constructor
     * @param x
     * @param y
     */
    public WoodCutter(int x, int y) {
        super(x, y);
        toughness = 0;
        woodCutterImage = new Image[4];
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
            woodCutterImage[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"woodcutter_4.png"));
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
     * Choose image for woodcutter for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    private void imageChooser(int toughness){
        if(toughness < 33) {
            setCurrentImage(woodCutterImage[0]);
        }
        else if(toughness > 33 && toughness < 66){
                setCurrentImage(woodCutterImage[1]);
        }
        else if(toughness > 66 && toughness < 99){
                setCurrentImage(woodCutterImage[2]);
        }
        else{
            setCurrentImage(woodCutterImage[3]);
        }
    }

}
