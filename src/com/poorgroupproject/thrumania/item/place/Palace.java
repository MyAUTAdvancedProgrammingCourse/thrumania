package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.human.Human;
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
public class Palace extends Place{

    private Image[] palaceImages = new Image[3];
    private ArrayList<Human> humanArrayList = new ArrayList<>();

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Palace(int x, int y) {
        super(x,y);
        loadResoure();
        setCurrentImage(palaceImages[2]);
        for (int i = 0; i < 10; i++){
            //10 nafar avalie bayad dashte bashad
        }

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setX(getX() + 2);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();
    }


    /**
     * loading images of palaces that first element of array is incompleted palace and the last one is completed palace.
     */
    @Override
    public void loadResoure() {
        palaceImages = new Image[3];
        try {
            palaceImages[0] = ImageIO.read(new File(ResourcePath.itemImagePath + "palace/palace_1.png"));
        } catch (IOException e) {
            System.err.println("FILE NOT FOUND");
            e.printStackTrace();
        }
    }

    @Override
    public void processEvent(Event event) {

    }

    private void makeHuman(){

    }

    public ArrayList<Human> getHumanArrayList() {
        return humanArrayList;
    }

    public void setHumanArrayList(ArrayList<Human> humanArrayList) {
        this.humanArrayList = humanArrayList;
    }


}
