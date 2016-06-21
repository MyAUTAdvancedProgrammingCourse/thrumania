package com.poorgroupproject.thrumania.item.human;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author saman
 * @version 1.0.0
 */
public class Citizen extends Human {

    private Image[] images;

    private int imageIndex;

    public Citizen(){
        super(0,0);

        images = new Image[16];
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("resource/image/item/woman/" + (i + 1) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        imageIndex = 0;
        setCurrentImage(images[imageIndex]);
        Timer t = new Timer();
        TimerTask task = new CitizenWalker(this);
        t.schedule(task,0,40);
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        while (true) {
            int x = getX();
            int y = getY();

            setX(x + 10);
            setY(y + 10);

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void nextImage(){
        imageIndex = (imageIndex + 1) % images.length;
        setCurrentImage(images[imageIndex]);
    }
}

class CitizenWalker extends TimerTask{

    private Citizen citizen;

    public CitizenWalker(Citizen citizen){
        this.citizen = citizen;
    }
    @Override
    public void run() {
        citizen.nextImage();
    }
}
