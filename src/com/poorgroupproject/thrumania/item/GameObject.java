package com.poorgroupproject.thrumania.item;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author ahmad
 * @version 1.0.0
 */
public abstract class GameObject{
    private Rectangle boundry;
    private Image currentImage;

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
}
