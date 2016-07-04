package com.poorgroupproject.thrumania.item.human;
import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.events.GoThePlaceEvent;
import com.poorgroupproject.thrumania.item.place.Place;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.*;
import com.poorgroupproject.thrumania.*;
import java.awt.*;
import com.poorgroupproject.thrumania.item.place.mine.*;
/**
 * @author saman
 * @version 1.0.0
 */
public abstract class Human extends GameObject implements Runnable,Constants{
    private final int HUMAN_WIDTH = 250;
    private final int HUMAN_HEIGHT = 250;
    CurrentTask currentTask;
    Path currentPath;
    Oriention upcomingOriention;
    public int life;
    Pair save;
    Place currentBuilding;
    Mine currentMine;
    Human AttackingTo;
//    public Land.Cell CurrentCell;
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
    int stepWise;
    public Human(int x, int y){
        super(x, y, 30, 30);
        isMoving = false;
        movingSyle = MovingSyle.WALKING;
        currentPath = null;
        this.currentTask = CurrentTask.StandingDoinfNothing;
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
    public abstract Image rightNow();
    public void Updateoriention(){
        System.out.println("path size   " + currentPath.path.size());
        if(currentTask == CurrentTask.Moving) {
            if (currentPath.path.size() == 0) {
                this.currentTask = CurrentTask.StandingDoinfNothing;
                this.setCurrentImage(rightNow());
                currentPath = null;
            } else
                oriention = DefineOreintion(this.getLocationOnMatrix(), currentPath.getNextMove());
        }
        else {
            if (currentPath.path.size() == 0) {
//                this.currentTask = CurrentTask.StandingDoinfNothing;
                this.setCurrentImage(rightNow());
                currentPath = null;
            } else
                oriention = DefineOreintion(this.getLocationOnMatrix(), currentPath.getNextMove());
        }
    }
    public int getSpeed(){
        switch(Season.getInstance().getCurrentSeason()){
            case Spring:
                return SpringSpeed;
            case Summer:
                return SummerSpeed;
            case Fall:
                return FallSpeed;
            case Winter:
                return WinterSpeed;
        }
        return 0;
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
    public void pathSolver(Path path){
        while(!path.ReachedthePath()){

        }
    }



    @Override
    public void processEvent(com.poorgroupproject.thrumania.events.Event event) {
        if(event instanceof GoThePlaceEvent){
            GoThePlaceEvent gt = (GoThePlaceEvent) event;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),this.getLocationOnMatrix().getY(),gt.targetX,gt.targetY,new Citizen(0,0,Oriention.Down),100,100);
            PathfindingRunnable pfr = new PathfindingRunnable(pf);
            (new Thread(pfr)).start();
            currentPath = pfr.path;
            currentTask = CurrentTask.Moving;
            oriention = DefineOreintion(this.getLocationOnMatrix(),currentPath.getNextMove());
            stepWise = 0;
            this.setCurrentImage(null);
        }
    }

}
class PathfindingRunnable implements Runnable{
    PathFinder pf;
    Path path;
    public PathfindingRunnable(PathFinder pf) {
        this.pf = pf;
    }

    @Override
    public void run() {
        path = pf.pathFinder();
    }
}
