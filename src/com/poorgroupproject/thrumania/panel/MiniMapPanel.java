package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.land.Land;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class MiniMapPanel{
    private BufferedImage view;
    private Rectangle boundry;
    private GamePanel gamePanel;

    public MiniMapPanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        boundry = new Rectangle();
        boundry.setLocation(0,0);
        boundry.setSize(200,100);
        view = new BufferedImage(300,200,BufferedImage.TYPE_INT_ARGB);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                }
            }
        })).start();
    }


    public Rectangle getBoundry(){
        return boundry;
    }
    public BufferedImage getView(){
        return view;
    }
    public void setLocation(Point location){
        boundry.setLocation(location);
    }
    public Point getLocation(){
        return boundry.getLocation();
    }
}
