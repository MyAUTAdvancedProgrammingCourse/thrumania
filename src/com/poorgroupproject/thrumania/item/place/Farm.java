package com.poorgroupproject.thrumania.item.place;

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

    public Farm(int x, int y) {
        super(x, y);
        farmImage = new Image[4];
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
    public void processEvent(Event event) {

    }
}
