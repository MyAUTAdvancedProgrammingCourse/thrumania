package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.*;
import com.poorgroupproject.thrumania.item.place.Port;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Cell;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.PathFinder;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *@author yahya
 * @version 1.0.0
 */
public class FishingShip extends Ship  {
    Image [] images;
    Port port;
    int peopleNum;
    final int peopleCapacity=1200;
    int foodNum=0;

    int shipCapacity;
    Human []fisherMan;
    Image fishingShip;



    public FishingShip(int x, int y,Oriention o) {
        super(x, y, 100,100 );
        //this.port=port;
        fisherMan =new Human[7];
        shipSpeed = 6;
        this.oriention = o;
        this.currentTask = CurrentTask.StandingDoinfNothing;
        this.setCurrentImage(rightNow());
    }



    public void getHuman(){
        for(int i=0;i<7;i++){
           // fisherMan[i]=pickUpHuman;
        }
    }

    public void IsEmpty(){
       shipCapacity= peopleNum *peopleCapacity;
        if(foodNum==shipCapacity){
            //send event to people stop fishing
            //chang the image
            Human h =new Citizen(0,0, Oriention.Down);
            h.processEvent(new StopFishingEvent(this));


        }
    }

    public Image rightNow() {
        /////////
        switch(oriention){
            case Up:
                return images[0];
            case UpRight:
                return images[1];
            case Right:
                return images[2];
            case DownRight:
                return images[3];
            case Down:
                return images[4];
            case DownLeft:
                return images[5];
            case Left:
                return images[6];
            case UpLeft:
                return images[7];
        }
        return null;

    }

        @Override
    public void loadResoure() {
        images = new Image[8];
        images[0] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/u.png");
        images[1] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/ur.png");
        images[2] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/r.png");
        images[3] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/dr.png");
        images[4] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/d.png");
        images[5] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/dl.png");
        images[6] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/l.png");
        images[7] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/ul.png");
    }

    @Override
    public void processEvent(Event event) {
        if(event instanceof GoThePlaceEvent){
         //   AttackingTo = null;
            GoThePlaceEvent gt = (GoThePlaceEvent) event;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),
                    this.getLocationOnMatrix().getY(),gt.targetX,gt.targetY,this,
                    Land.getInstance().getRows(),Land.getInstance().getCols());
            // PathfindingRunnable pfr = new PathfindingRunnable(pf);
            //  (new Thread(pfr)).start();
            currentPath = pf.pathFinder();
            System.out.print(currentPath.path.size());
            if(currentPath != null) {
                for (Pair p : currentPath.path)
                    System.out.println(p.getX() + "   " + p.getY());
                currentTask = CurrentTask.Moving;
                currentPath.path.remove(0);
                System.out.println("Now here"+this.getLocationOnMatrix().getX() + "  " + this.getLocationOnMatrix().getY());
                System.out.println("the path");
                for (Pair p : currentPath.path)
                    System.out.println(p.getX() + "   " + p.getY());
                this.Updateoriention();
                stepWise = 0;
               this.setCurrentImage(rightNow());
            }
        }
        else if (event instanceof GoAndCollectResourceEvent){

            GoAndCollectResourceEvent ga = (GoAndCollectResourceEvent) event;
            currentTask = CurrentTask.ResourceCollecting;
            //currentBuilding = ga.place;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),
                    this.getLocationOnMatrix().getX(),
                    this.getLocationOnMatrix().getY(),
                    ga.target.getX(),ga.target.getY(),
                    new Citizen(0,0,Oriention.Down),
                    Land.getInstance().getRows(),Land.getInstance().getCols());
            currentPath = pf.pathFinder();
            if(currentPath != null)
                currentPath.path.remove(0);
            if(currentPath != null && currentPath.path.size() != 0) {
                this.Updateoriention();
                stepWise = 0;
                this.setCurrentImage(rightNow());
            }
            else {
                currentPath = null;
                stepWise = 0;
            }
        }

    }

    @Override
    public void tik() {
        lifeCounter += 3;
        if(lifeCounter >= 1000)
            getPlayer().setFood(getPlayer().getFood()-1);
        switch(currentTask) {
            case Moving:

                switch (oriention) {
                    case Up:
                        moveUp();
                        stepWise += getSpeed();
                        break;
                    case UpRight:
                        moveUpRight();
                        stepWise += getSpeed();
                        break;
                    case Right:
                        moveRight();
                        stepWise += getSpeed();
                        break;
                    case DownRight:
                        moveDownRight();
                        stepWise += getSpeed();
                        break;
                    case Down:
                        moveDown();
                        stepWise += getSpeed();
                        break;
                    case DownLeft:
                        moveDownLeft();
                        stepWise += getSpeed();
                        break;
                    case Left:
                        moveLeft();
                        stepWise += getSpeed();
                        break;
                    case UpLeft:
                        moveUpLeft();
                        stepWise += getSpeed();
                        break;
                }

                if (stepWise >= 120) {
                    System.out.println(currentPath.path.size());
                    this.Updateoriention();
                   this.setCurrentImage(rightNow());
                    stepWise = 0;
                    System.out.println(stepWise);

//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                }

                break;
            case ResourceCollecting:
                if(currentPath != null) {
                    switch (oriention) {
                        case Up:
                            moveUp();
                            stepWise += getSpeed();
                            break;
                        case UpRight:
                            moveUpRight();
                            stepWise += getSpeed();
                            break;
                        case Right:
                            moveRight();
                            stepWise += getSpeed();
                            break;
                        case DownRight:
                            moveDownRight();
                            stepWise += getSpeed();
                            break;
                        case Down:
                            moveDown();
                            stepWise += getSpeed();
                            break;
                        case DownLeft:
                            moveDownLeft();
                            stepWise += getSpeed();
                            break;
                        case Left:
                            moveLeft();
                            stepWise += getSpeed();
                            break;
                        case UpLeft:
                            moveUpLeft();
                            stepWise += getSpeed();
                            break;
                    }

                    if (stepWise >= 120) {
                        System.out.println(currentPath.path.size());
                        this.Updateoriention();
                        this.setCurrentImage(rightNow());
                        stepWise = 0;
                        System.out.println(stepWise);
//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                    }
                }
                else{
                    Cell[][] map = Land.getInstance().getCells();
                    Pair temp = getLocationOnMatrix();
                    counter = 4;
                    counter = counter % 1000;
                    if(counter > 400){
                        switch(map[temp.getX()][temp.getY()]){
                            case FISH:
                                this.amount_of_food += 50;
                                this.Capacity += 50;
                        }
                    }
                    System.out.println(this.Capacity);
                    if(Capacity > 1200){
                        this.processEvent(new GoBacktoYourPalace(null));
                    }
                }
                break;
        }
    }

    public void setPort(Port port) {
        this.port = port;
    }
}
