package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author saman
 * @version 1.0.0
 */
public class Citizen extends Human {

 //   public int life;
    private int speed;
    public Citizen(int x, int y) {
        super(x, y);
        this.life = 300;
    }
    public int getSpeed(){
        switch(Season.getInstance().getCurrentSeason()){
            case Spring:
                return 8;
            case Summer:
                return 7;
            case Fall:
                return 5;
            case Winter:
                return 3;
        }
        return 0;
    }
    @Override
    public void loadResoure() {

    }

    @Override
    public void processEvent(Event event) {

    }

    @Override
    public void run() {

    }
}

//class CitizenWalker extends TimerTask{
//
//    private Citizen citizen;
//
//    public CitizenWalker(Citizen citizen){
//        this.citizen = citizen;
//    }
//    @Override
//    public void run() {
//        citizen.nextImage();
//    }
//}
//    private Image[] images;
//
//    private int imageIndex;
//
//    public Citizen(){
//        super(0,0);
//
//        images = new Image[16];
//        try {
//            for (int i = 0; i < images.length; i++) {
//                images[i] = ImageIO.read(new File("resource/image/item/woman/" + (i + 1) + ".png"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//        imageIndex = 0;
//        setCurrentImage(images[imageIndex]);
//        Timer t = new Timer();
//        TimerTask task = new CitizenWalker(this);
//        t.schedule(task,0,40);
//        (new Thread(this)).start();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            int x = getX();
//            int y = getY();
//
//            setX(x + 10);
//            setY(y + 10);
//
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void nextImage(){
//        imageIndex = (imageIndex + 1) % images.length;
//        setCurrentImage(images[imageIndex]);
//    }
//
//    @Override
//    public void processEvent(com.poorgroupproject.thrumania.events.Event event) {
//        if (event instanceof ClickEvent){
//            System.out.println("player clicked");
//        }
//    }