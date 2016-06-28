package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.ConstructPlaceEvent;
import com.poorgroupproject.thrumania.events.DestroyPlaceEvent;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Soldier;
import com.poorgroupproject.thrumania.util.ResourcePath;

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

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Barrack(int x, int y) {
        super(x, y);
        loadResoure();
        barrackImages = new Image[4];
        soldierArrayList = new ArrayList<>();
        setToughness(0);
    }



    /**
     * loading images of barracks that first element of array is incompleted barrack and the last one is completed barrack.
     */
    @Override
    public void loadResoure() {
        try {
            barrackImages[0]= ImageIO.read(new File(ResourcePath.itemImagePath+"barrack\\barrack_1.png"));
            barrackImages[1]= ImageIO.read(new File(ResourcePath.itemImagePath+"barrack\\barrack_2.png"));
            barrackImages[2]= ImageIO.read(new File(ResourcePath.itemImagePath+"barrack\\barrack_3.png"));
            barrackImages[3]= ImageIO.read(new File(ResourcePath.itemImagePath+"barrack\\barrack_4.png"));
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
     * Choose image for barrack for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    public void imageChooser(int toughness){
        if(toughness < 33) setCurrentImage(barrackImages[0]);
        else if(toughness > 33 && toughness < 66) setCurrentImage(barrackImages[1]);
        else if(toughness > 66 && toughness < 99) setCurrentImage(barrackImages[2]);
        else setCurrentImage(barrackImages[3]);
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
