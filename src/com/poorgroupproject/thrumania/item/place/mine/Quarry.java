package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author amin
 * @version 1.0.0
 */
public class Quarry extends Mine {

    private int availabeResource;
    private int toughness;
    private Image[] quarry;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Quarry(int x, int y) {
        super(x,y);
        availabeResource = 0;
        toughness = 100;
        quarry = new Image[4];
    }
    /**
     * loading images of barracks that first element of array is incompleted quarry and the last one is completed barrack.
     */
    @Override
    public void loadResoure() {
        try {
            quarry[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_1.png"));
            quarry[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_2.png"));
            quarry[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_3.png"));
            quarry[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_4.png"));
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
     * Choose image for quarry for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    private void imageChooser(int toughness){
        if(toughness < 33) setCurrentImage(quarry[0]);
        else {
            if (toughness > 33 && toughness < 66) {
                setCurrentImage(quarry[1]);
            } else {
                if (toughness > 66 && toughness < 99) {
                    setCurrentImage(quarry[2]);
                } else {
                    setCurrentImage(quarry[3]);
                }
            }
        }
    }
}
