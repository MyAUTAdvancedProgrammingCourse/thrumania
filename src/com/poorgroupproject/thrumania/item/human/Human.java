package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.item.GameObject;

import java.awt.*;

/**
 * @author saman
 * @version 1.0.0
 */
public abstract class Human extends GameObject implements Runnable{
    private final int HUMAN_WIDTH = 250;
    private final int HUMAN_HEIGHT = 250;
    public int life;
    public int Capacity;
    public boolean isMoving;
    public enum MovingSyle{
        WALKING, RUNNING
    };
    Oriention oriention;
    public MovingSyle movingSyle;

    public Human(int x, int y){
        super(x, y, 250, 250);
        isMoving = false;
        movingSyle = MovingSyle.WALKING;
    }
    public void moveUp(){
        this.setY(this.getY()-20);
    }
    public void moveDown(){
        this.setY(this.getY()+20);
    }
    public void moveRight(){
        this.setX(this.getX()+20);
    }
    public void moveLeft(){
        this.setX(this.getX()-20);
    }
    public void moveUpRight(){
        moveUp();
        moveRight();
    }
    public void moveUpLeft(){
        moveUp();
        moveLeft();
    }
    public void moveDownRight(){
        moveDown();
        moveRight();
    }
    public void moveDownLeft(){
        moveDown();
        moveLeft();
    }
    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
