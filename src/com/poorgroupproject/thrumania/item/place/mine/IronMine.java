package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.place.Place;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Amin on 6/21/2016.
 */
public class IronMine extends Mine {

    private Image[] ironMineImages;

    public IronMine(int x, int y, int toughness, int availableResource) {
        super(x, y, toughness, availableResource);
        ironMineImages = new Image[4];
    }

    @Override
    public void loadResoure() {
        try {
            ironMineImages[0]= ImageIO.read(new File("resource\\image\\item\\mine\\ironMine\\ironMine_1.png"));
            ironMineImages[1]= ImageIO.read(new File("resource\\image\\item\\mine\\ironMine\\ironMine_2.png"));
            ironMineImages[2]= ImageIO.read(new File("resource\\image\\item\\mine\\ironMine\\ironMine_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
