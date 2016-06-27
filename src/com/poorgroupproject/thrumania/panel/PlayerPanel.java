package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.util.GameEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class PlayerPanel{
    private BufferedImage view;
    private Rectangle boundry;
    public PlayerPanel(){
        boundry = new Rectangle();
        boundry.setLocation(0,0);
        boundry.setSize(100,100);
        try {
            view = ImageIO.read(new File("resource/image/item/spring.png"));
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
}
