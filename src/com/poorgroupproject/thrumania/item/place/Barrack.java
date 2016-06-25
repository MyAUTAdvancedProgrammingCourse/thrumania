package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Soldier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author amin
 * @version 1.0.0
 */
public class Barrack extends Place {

    private Image[] barrackImages;
    private ArrayList<Soldier> soldierArrayList;

    private Barrack(int toughness) {
        super(toughness);

    }

    public Barrack(int x, int y, int toughness) {
        super(x, y, toughness);
        barrackImages = new Image[3];
        soldierArrayList = new ArrayList<Soldier>();
    }

    @Override
    public void loadResoure() {
        try {
            barrackImages[0]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"barrack\\barrack_1.png"));
            barrackImages[1]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"barrack\\barrack_2.png"));
            barrackImages[2]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"barrack\\barrack_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }

    public void makeSoldier(ArrayList<Soldier> ms){
        ms.add(new Soldier(getX(),getY()));
    }

    public ArrayList<Soldier> getSoldierArrayList() {
        return soldierArrayList;
    }

    public void setSoldierArrayList(ArrayList<Soldier> soldierArrayList) {
        this.soldierArrayList = soldierArrayList;
    }
}
