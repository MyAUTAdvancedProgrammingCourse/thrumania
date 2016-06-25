package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Human;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Amin on 6/21/2016.
 */
public class Palace extends Place{

    private int x;
    private int y;
    private int width;
    private int height;
    private int availabeGold;
    private int availabeIron;
    private int availabeFood;
    private int availabeWood;
    private Image thirtyPercentPalace;
    private Image sixtyPercentPalace ;
    private Image oneHundredPercentPalace;


    public Palace(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;





        for (int i = 0; i < 10; i++){
            getHumanArrayList().add(new Human() {
                @Override
                public void run() {
                    //inja bayad moshakhasate humane sakhte shode tarif beshe
                }
            });
        }

    }

    @Override
    public void loadResoure() {
        thirtyPercentPalace = ImageIO.read(new File("resource\\image\\item\\palace\\palace_1.png"));
        sixtyPercentPalace = ImageIO.read(new File("resource\\image\\item\\palace\\palace_2.png"));
        oneHundredPercentPalace = ImageIO.read(new File("resource\\image\\item\\palace\\palace_3.png"));
    }

    @Override
    public void processEvent(Event event) {

    }

    public void setPalaceCurrentImage() {
        switch (getToughness()){
            case 1: setCurrentImage(thirtyPercentPalace); break;
            case 2: setCurrentImage(sixtyPercentPalace); break;
            case 3: setCurrentImage(oneHundredPercentPalace); break;
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

}
