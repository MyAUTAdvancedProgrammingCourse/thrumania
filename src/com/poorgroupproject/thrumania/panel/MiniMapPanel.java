package com.poorgroupproject.thrumania.panel;

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

    public MiniMapPanel(){
        boundry = new Rectangle();
        boundry.setLocation(0,0);
        boundry.setSize(200,100);
        try {
            view = ImageIO.read(new File("resource/image/item/woman/2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
