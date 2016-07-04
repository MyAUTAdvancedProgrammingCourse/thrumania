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
    private Image[] quarryImage;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Quarry(int x, int y) {
        super(x,y);
        loadResoure();
        availabeResource = 0;
        setToughness(0);
    }
    /**
     * loading images of barracks that first element of array is incompleted quarry and the last one is completed barrack.
     */
    @Override
    public void loadResoure() {
        quarryImage = new Image[4];
        try {
            quarryImage[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_1.png"));
            quarryImage[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_2.png"));
            quarryImage[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_3.png"));
            quarryImage[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"quarry/quarry_4.png"));
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
        if(toughness < 13) setCurrentImage(quarryImage[0]);
        else if (toughness > 13 && toughness < 26) setCurrentImage(quarryImage[1]);
        else if (toughness > 26 && toughness < 39) setCurrentImage(quarryImage[2]);
        else setCurrentImage(quarryImage[3]);
    }
}
