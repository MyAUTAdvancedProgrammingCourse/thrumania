package com.poorgroupproject.thrumania.item.human;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Path;
import java.awt.*;

/**
 * @author saman
 * @version 1.0.0
 */
public abstract class Human extends GameObject implements Runnable,Constants{
    private final int HUMAN_WIDTH = 250;
    private final int HUMAN_HEIGHT = 250;
    public int life;
    public Land.Cell CurrentCell;
    public int Capacity;
    int amount_of_gold;
    int amount_of_iron;
    int amount_of_wood;
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
        this.setY(this.getY()-SpeedCord);
    }
    public void moveDown(){
        this.setY(this.getY()+SpeedCord);
    }
    public void moveRight(){
        this.setX(this.getX()+SpeedCord);
    }
    public void moveLeft(){
        this.setX(this.getX()-SpeedCord);
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
    public Oriention DefineOreintion(Pair first,Pair second){
        int xDif = second.getX() - first.getX();
        int yDif = second.getY() - first.getY();
        if(second.getY() - first.getY() == 0){
            if(second.getX() - first.getX() == -1){
                return Oriention.Up;
            }
            if(second.getX() - first.getX() == 1){
                return Oriention.Down;
            }
        }
        if(second.getX() - first.getX() == 0){
            if(second.getY() - first.getY() == 1){
                return Oriention.Right;
            }
            if(second.getY() - first.getY() == -1){
                return Oriention.Left;
            }
        }
        if(xDif == 1 && yDif == 1)
            return Oriention.DownRight;
        if(xDif == 1 && yDif == -1)
            return Oriention.DownLeft;
        if(xDif == -1 && yDif == 1)
            return Oriention.UpRight;
        if(xDif == -1 && yDif == -1)
            return Oriention.UpLeft;
        return null;
    }
    public void pathSolver(Path path){
        while(!path.ReachedthePath()){

        }
    }
}
