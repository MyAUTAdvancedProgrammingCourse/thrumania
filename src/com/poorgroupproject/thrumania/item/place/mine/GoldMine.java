package com.poorgroupproject.thrumania.item.place.mine;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.place.Place;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author amin
 * @version 1.0.0
 */
public class GoldMine extends Mine {

    private Image[] goldMineImages;


    GoldMine(int x, int y, int toughness, int availableResource) {
        super(x, y, toughness, availableResource);
        goldMineImages = new Image[4];
    }

    @Override
    public void loadResoure() {
        try {
            goldMineImages[0]= ImageIO.read(new File("resource\\image\\item\\mine\\goldMine\\goldMine_1.png"));
            goldMineImages[1]= ImageIO.read(new File("resource\\image\\item\\mine\\goldMine\\goldMine_2.png"));
            goldMineImages[2]= ImageIO.read(new File("resource\\image\\item\\mine\\goldMine\\goldMine_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }
}
