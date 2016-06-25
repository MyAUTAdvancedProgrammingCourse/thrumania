package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Human;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author amin
 * @version 1.0.0
 */
public class Palace extends Place{

    private int availabeGold;
    private int availabeIron;
    private int availabeFood;
    private int availabeWood;
    private Image[] palaceImages = new Image[3];
    private ArrayList<Human> humanArrayList = new ArrayList<>();

    public Palace(int x, int y) {
        super(x,y)
        loadResoure();

        for (int i = 0; i < 10; i++){
            getHumanArrayList().add(new Human() {
                @Override
                public void run() {
                    //inja bayad moshakhasate humane sakhte shode tarif beshe
                }
            });
        }
    }

    Palace(int x, int y, int toughness) {
        super(x, y, toughness);
    }

    @Override
    public void loadResoure() {
        try {
            palaceImages[0]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"palace\\palace_1.png"));
            palaceImages[1]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"palace\\palace_2.png"));
            palaceImages[2]= ImageIO.read(new File(ConstantPlaceVariables.resourceItemPath+"palace\\palace_3.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }

    public void setPalaceCurrentImage() {
        switch (getToughness()){
            case 1: setCurrentImage(palaceImages[0]); break;
            case 2: setCurrentImage(palaceImages[1]); break;
            case 3: setCurrentImage(palaceImages[2]); break;
            default: setCurrentImage(null);
        }
    }

    private void makeHuman(){
        setHumanArrayList(getHumanArrayList().add(new Human() {
            @Override
            public void run() {
                //inja bayad moshakhasate humane sakhte shode tarif beshe
            }
        }));
    }

    public ArrayList<Human> getHumanArrayList() {
        return humanArrayList;
    }

    public void setHumanArrayList(ArrayList<Human> humanArrayList) {
        this.humanArrayList = humanArrayList;
    }

}
