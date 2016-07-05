package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.util.GameConfig;
import com.sun.org.apache.bcel.internal.generic.LAND;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class MiniMapPanel{
    private BufferedImage view;
    private Rectangle boundry;
    private GamePanel gamePanel;
    private double scale=0.001;
    private ArrayList<GameObject> objects;

    public MiniMapPanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        boundry = new Rectangle();
        boundry.setLocation(0,0);
        boundry.setSize(((int) (Land.getInstance().getMapWidth() * scale)), ((int) (Land.getInstance().getMapHeight() * scale)));
        view = new BufferedImage(300,200,BufferedImage.TYPE_INT_ARGB);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    objects= gamePanel.getGameObjects();
                    BufferedImage temp;
                    temp = new BufferedImage(((int) boundry.getWidth()), ((int) boundry.getHeight()),BufferedImage.TYPE_3BYTE_BGR);
                    Graphics graphics = view.getGraphics();
                    for (GameObject go :
                            objects) {
                        int x = ((int) (go.getX() * scale));
                        int y = ((int) (go.getY() * scale));
                        int w = ((int) (go.getBoundry().getWidth() * scale));
                        int h = ((int) (go.getBoundry().getHeight() * scale));
                        graphics.setColor(Color.BLUE);
                        graphics.fillRect(x,y,w,h);
                        try {
                            Thread.sleep(GameConfig.frameDelayTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    view = temp;
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
