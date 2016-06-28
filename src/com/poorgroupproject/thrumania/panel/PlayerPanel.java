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
    private Image player;
    private Dimension playerFaceDimension;

    public PlayerPanel(){
        boundry = new Rectangle();
        boundry.setLocation(0,0);
        boundry.setSize(200,400);
        playerFaceDimension = new Dimension(50,50);
        try {
            view = ImageIO.read(new File("resource/image/item/spring.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics graphics = view.getGraphics();
        graphics.setColor(Color.gray);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 140));
        graphics.drawString("some text", 110, ((int) (.5 * boundry.getHeight())));
        try {
            player = ImageIO.read(new File("resource/image/player/face.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics.drawImage(player,(((int) (boundry.getWidth() - playerFaceDimension.getWidth()))) / 2, ((int) (.3 * boundry.getHeight()))
                , ((int) playerFaceDimension.getWidth()), ((int) playerFaceDimension.getHeight()),null);
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
