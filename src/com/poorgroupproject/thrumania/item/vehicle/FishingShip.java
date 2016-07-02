package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.events.ClickEvent;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.events.GoThePlaceEvent;
import com.poorgroupproject.thrumania.events.StopFishingEvent;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.*;
import com.poorgroupproject.thrumania.item.place.Port;
import com.poorgroupproject.thrumania.land.Land;
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

    Port port;
    int peopleNum;
    final int peopleCapacity=1200;
    int foodNum=0;

    int shipCapacity;
    Human []fisherMan;
    Image fishingShip;



    public FishingShip(int x, int y) {
        super(x, y, 256, 256);
        //this.port=port;
        fisherMan =new Human[7];
        setCurrentImage(fishingShip);
        shipSpeed = 6;
    }



    public void getHumqn(){
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

    @Override
    public void loadResoure() {
        try {
            fishingShip = ImageIO.read(new File(ResourcePath.fishingFishPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void processEvent(Event event) {
        if(event instanceof GoThePlaceEvent){
         //   AttackingTo = null;
            System.out.println("hereeeeeeeeeee");
            GoThePlaceEvent gt = (GoThePlaceEvent) event;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),this.getLocationOnMatrix().getY(),gt.targetX,gt.targetY,new Citizen(0,0,Oriention.Down),
                    Land.getInstance().getRows(),Land.getInstance().getCols());
           // PathfindingRunnable pfr = new PathfindingRunnable(pf);
            //  (new Thread(pfr)).start();
            currentPath = pf.pathFinder();
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
//                this.setCurrentImage(rightNow());
            }
        }

    }

    @Override
    public void tik() {
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
//                    this.setCurrentImage(rightNow());
                    stepWise = 0;
                    System.out.println(stepWise);

//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                }

                break;
        }
    }

    public void setPort(Port port) {
        this.port = port;
    }
}
