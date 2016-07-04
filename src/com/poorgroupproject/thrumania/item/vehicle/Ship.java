package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.CurrentTask;
import com.poorgroupproject.thrumania.item.human.Oriention;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.Path;

/**
 * Created by ahmad on 5/31/16.
 */
public abstract class Ship extends GameObject {
    int shipSpeed;
    Path currentPath;
    int counter;
    int amount_of_food;
    int lifeCounter;
    int Capacity;
    int stepWise;
    Oriention oriention;
    CurrentTask currentTask;
    public Ship(int x, int y, int width, int height) {
        super(x, y, width, height);
        counter = 0;
        lifeCounter = 0;
        Capacity = 0;
        amount_of_food = 0;
    }
    public int  getSpeed(){
        switch(Season.getInstance().getCurrentSeason()){
            case Winter:
                return 0;
            default:
                return shipSpeed;
        }
    }
    public void moveUp(){
        this.setY(this.getY()-getSpeed());
    }
    public void moveDown(){
        this.setY(this.getY()+getSpeed());
    }
    public void moveRight(){
        this.setX(this.getX()+getSpeed());
    }
    public void moveLeft(){
        this.setX(this.getX()-getSpeed());
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
    public Oriention DefineOreintion(Pair first, Pair second){
        int xDif = second.getX() - first.getX();
        int yDif = second.getY() - first.getY();
        if(yDif > 0){
            if(xDif < 0)
                return Oriention.UpRight;
            if(xDif == 0)
                return Oriention.Right;
            if(xDif > 0)
                return Oriention.DownRight;
        }
        else if(yDif == 0){
            if(xDif < 0)
                return Oriention.Up;
            if(xDif == 0) {
                System.out.println("buggggggggg");
                return this.oriention;
            }
            if(xDif > 0)
                return Oriention.Down;
        }
        else{
            if(xDif < 0)
                return Oriention.UpLeft;
            if(xDif == 0)
                return Oriention.Left;
            if(xDif > 0)
                return Oriention.DownLeft;
        }
        System.out.println("Coldn't because " + xDif +"  " + yDif);
        return null;
    }
    public void Updateoriention(){
        System.out.println("path size   " + currentPath.path.size());
        if(currentPath.path.size()== 0){
            this.currentTask = CurrentTask.StandingDoinfNothing;
//            this.setCurrentImage(rightNow());
            currentPath = null;
        }
        else
            oriention = DefineOreintion(this.getLocationOnMatrix(),currentPath.getNextMove());
    }

}
