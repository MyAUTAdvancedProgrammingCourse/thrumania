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
public class Tree extends Place {

    private int treeAvailabeWood;
    private Image[] treeImage;
    private int toughness;

    public Tree(int x, int y) {
        super(x, y);
        treeAvailabeWood = 10000;
        toughness = 100;
    }

    @Override
    public void loadResoure() {
        treeImage = new Image[4];
        try {
            treeImage[0] = ImageIO.read(new File(ResourcePath.itemImagePath + "tree\tree_1.png"));
            treeImage[1] = ImageIO.read(new File(ResourcePath.itemImagePath + "tree\tree_2.png"));
            treeImage[2] = ImageIO.read(new File(ResourcePath.itemImagePath + "tree\tree_3.png"));
            treeImage[3] = ImageIO.read(new File(ResourcePath.itemImagePath + "tree\tree_4.png"));
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
     * Choose image for tree for each amount of toughness.
     * @param toughness the amount of toughness that range is 0 to 100.
     */
    public void imageChooser(int toughness){
        if(toughness < 33) setCurrentImage(treeImage[0]);
        else if(toughness > 33 && toughness < 66) setCurrentImage(treeImage[1]);
        else if(toughness > 66 && toughness < 99) setCurrentImage(treeImage[2]);
        else setCurrentImage(treeImage[3]);
    }

}
