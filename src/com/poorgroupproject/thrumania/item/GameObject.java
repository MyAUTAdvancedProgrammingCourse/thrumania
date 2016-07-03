package com.poorgroupproject.thrumania.item;

import com.poorgroupproject.thrumania.Player.Player;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Pair;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * @author ahmad
 * @version 1.0.0
 */
public abstract class GameObject{
    private Rectangle boundry;
    private Image currentImage;

    public void setPlayer(Player player) {
        this.player = player;
    }

    Player player;

    public Player getPlayer() {
        return player;
    }

    public GameObject(int x, int y, int width, int height){
        boundry = new Rectangle(x, y, width, height);
        loadResoure();
    }
    public Rectangle getBoundry() {
        return boundry;
    }
    public void setBoundry(Rectangle boundry) {
        this.boundry = boundry;
    }

    public synchronized void setX(int x){
        int y = ((int) boundry.getY());
        boundry.setLocation(x, y);
    }
    public synchronized void setY(int y){
        int x = ((int) boundry.getX());
        boundry.setLocation(x, y);
    }
    public synchronized int getX(){
        return ((int) boundry.getX());
    }
    public synchronized int getY() {
        return ((int) boundry.getY());
    }
    public abstract void loadResoure();
    public Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }

    public abstract void processEvent(com.poorgroupproject.thrumania.events.Event event);

    public void makeSelected(){
        BufferedImage temp = new BufferedImage(((int) boundry.getWidth()), ((int) boundry.getHeight()),BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = temp.getGraphics();
        graphics.drawImage(currentImage,0,0, ((int) boundry.getWidth()), ((int) boundry.getHeight()),null);
        graphics.setColor(Color.blue);
        graphics.fillRect(0,10,50,50);
        currentImage = temp;
    }

    public Pair getLocationOnMatrix(){
        Pair pair = new Pair(((int) boundry.getY()) / Land.CELL_HEIGHT,((int) boundry.getX()) / Land.CELL_WIDTH);
        return pair;
    }


    public static Pair getLocationOnMatrix(int x, int y){
        Pair pair = new Pair(y / Land.CELL_HEIGHT, x / Land.CELL_WIDTH);
        return pair;
    }
    public boolean readyforReOriention(Pair p){
//        int x = p.getX() * Land.CELL_WIDTH + (Land.CELL_WIDTH/2);
//        int y = p.getY() * Land.CELL_HEIGHT + (Land.CELL_HEIGHT/2);
//        int heuX = Math.abs(this.getX() - x);
//        int heuY = Math.abs(this.getY() - y);
        try {
            if (this.getLocationOnMatrix().getX() == p.getX() &&
                    this.getLocationOnMatrix().getY() == p.getY())
                return true;
        }
        catch(java.lang.NullPointerException e){
            return false;
        }
        return false;
    }
    public abstract void tik();

}
