package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.place.Barrack;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.PathFinder;
import com.poorgroupproject.thrumania.util.ResourcePath;

import java.awt.*;

/**
 * @author saman
 * @version 1.0.0
 */
public class Soldier extends Human {
    public Image[] images;
    //   public int life;
    private int speed;
    public boolean isCollectingResource;
    public Soldier(int x, int y,Oriention oriention) {
        super(x, y);
        this.life = 500;
        this.Capacity = 0;
        this.oriention = oriention;
        this.setCurrentImage(rightNow());
    }

    @Override
    public void loadResoure() {
        images = new Image[16];
        images[0] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_up.gif");
        images[1] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_upright.gif");
        images[2] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_right.gif");
        images[3] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_downright.gif");
        images[4] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_down.gif");
        images[5] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_downleft.gif");
        images[6] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_left");
        images[7] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "soldier/soldier_upleft.gif");
    }

    @Override
    public void processEvent(Event event) {
        System.out.println(this.getLocationOnMatrix().getX() + "   " + this.getLocationOnMatrix().getY());
        System.out.println("here");
        if(event instanceof CitizenAttackEvent){
            this.life -= 20;
            if(life <= 0){
                this.getGamePanel().getGameObjects().remove(this);
            }
        }
        else if(event instanceof SoldierAttackEvent){
            this.life -= 70;
            if(life <= 0){
                System.out.println(this.toString() + "   " + "dead");
                this.getGamePanel().getGameObjects().remove(this);
            }
        }
        else if(event instanceof GoThePlaceEvent){
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            System.out.println("hereeeeeeeeeee");
            GoThePlaceEvent gt = (GoThePlaceEvent) event;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),
                    this.getLocationOnMatrix().getY(),gt.targetX,gt.targetY,new Citizen(0,0,Oriention.Down),
                    Land.getInstance().getRows(),Land.getInstance().getCols());
            PathfindingRunnable pfr = new PathfindingRunnable(pf);
            System.out.println("we"+this.getLocationOnMatrix().getX()+"  "+this.getLocationOnMatrix().getY());
            System.out.println("their"+"  "+gt.targetX+"  "+gt.targetY);
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
                this.setCurrentImage(rightNow());
            }
        }
        else if(event instanceof GoAndAttack){
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            GoAndAttack ga = (GoAndAttack) event;
            currentTask = CurrentTask.AttackingToAHuman;
            AttackingTo = ga.target;

            System.out.println("Attack"+AttackingTo.getLocationOnMatrix().getX() + "  " + AttackingTo.getLocationOnMatrix().getY());
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),
                    this.getLocationOnMatrix().getX()
                    ,this.getLocationOnMatrix().getY()
                    ,AttackingTo.getLocationOnMatrix().getX()
                    ,AttackingTo.getLocationOnMatrix().getY()
                    ,new Citizen(0,0,Oriention.Down),
                    Land.getInstance().getRows(),Land.getInstance().getCols());
            System.out.println("we"+this.getLocationOnMatrix().getX()+"  "+this.getLocationOnMatrix().getY());
            System.out.println("their"+"  "+AttackingTo.getLocationOnMatrix().getX()+"  "+AttackingTo.getLocationOnMatrix().getY());
            //  PathfindingRunnable pfr = new PathfindingRunnable(pf);
            //  (new Thread(pfr)).start();
            currentPath = pf.pathFinder();
            //   System.out.println("Sizizizi"+currentPath.path.size());
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
        else if(event instanceof GoandBuildAPlace){
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            GoandBuildAPlace ga = (GoandBuildAPlace) event;
            currentTask = CurrentTask.BuildingABarrack;
            currentBuilding = ga.place;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),
                    this.getLocationOnMatrix().getX(),
                    this.getLocationOnMatrix().getY(),
                    ga.target.getX(),ga.target.getY(),
                    new Citizen(0,0,Oriention.Down),
                    Land.getInstance().getRows(),Land.getInstance().getCols());
            PathfindingRunnable pfr = new PathfindingRunnable(pf);
            //  (new Thread(pfr)).start();
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
    public Image rightNow(){
        switch(currentTask){
//            case StandingDoinfNothing:
//                switch(oriention){
//                    case Up:
//                        return images[8];
//                    case UpRight:
//                        return images[9];
//                    case Right:
//                        return images[10];
//                    case DownRight:
//                        return images[11];
//                    case Down:
//                        return images[12];
//                    case DownLeft:
//                        return images[13];
//                    case Left:
//                        return images[14];
//                    case UpLeft:
//                        return images[15];
//                }
//                break;case StandingDoinfNothing:
//                switch(oriention){
//                    case Up:
//                        return images[8];
//                    case UpRight:
//                        return images[9];
//                    case Right:
//                        return images[10];
//                    case DownRight:
//                        return images[11];
//                    case Down:
//                        return images[12];
//                    case DownLeft:
//                        return images[13];
//                    case Left:
//                        return images[14];
//                    case UpLeft:
//                        return images[15];
//                }
//                break;
            case Moving:
            case AttackingToAHuman:
            default:
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
                break;
        }

        return null;
    }
    ////////////////////////////////////////
    //////////
    ////
    @Override
    public void tik() {
        switch(currentTask){
            case StandingDoinfNothing:
//                for(GameObject go:this.getGamePanel().getGameObjects()){
//                    if(go instanceof Human && go != this){
//                        if(Math.abs(go.getX() - this.getX()) + Math.abs(go.getY() - this.getY()) < 160 && ((go.getX() - this.getX()) + (go.getY() - this.getY())) != 0){
//                            this.processEvent(new GoAndAttack(null,(Human)go));
//                        }
//                    }
//                }
                break;
            case Moving:
                for(GameObject go:this.getGamePanel().getGameObjects()){
                    if(go instanceof Human && go != this){
                        if(Math.abs(go.getX() - this.getX()) + Math.abs(go.getY() - this.getY()) < 400 && ((go.getX() - this.getX()) + (go.getY() - this.getY())) != 0){
                            this.processEvent(new GoAndAttack(null,(Human)go));
                        }
                    }
                }
                switch(oriention){
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

                if(stepWise >= 120 ){
                    System.out.println(currentPath.path.size());
                    this.Updateoriention();
                    this.setCurrentImage(rightNow());
                    stepWise = 0;
                    System.out.println(stepWise);

//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                }

                break;
            case AttackingToAHuman:
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
                        PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX()
                                ,this.getLocationOnMatrix().getY(),AttackingTo.getLocationOnMatrix().getX()
                                ,AttackingTo.getLocationOnMatrix().getY(),new Citizen(0,0,Oriention.Down),
                                Land.getInstance().getRows(),Land.getInstance().getCols());
                        PathfindingRunnable pfr = new PathfindingRunnable(pf);
                        //  (new Thread(pfr)).start();
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
//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                    }
                }
                else{
                    (new Thread(new SAttack(this))).start();

//                    PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),this.getLocationOnMatrix().getY(),AttackingTo.getLocationOnMatrix().getX(),AttackingTo.getLocationOnMatrix().getY(),new Citizen(0,0,Oriention.Down),
//                            Land.getInstance().getRows(),Land.getInstance().getCols());
//                    Path pt = pf.pathFinder();
//                    if(pt.path.size() > 1){
//                        AttackingTo = null;
//                    }
                    ////////
                    //
                    //
                    //
                    //
                }
                break;
            case AttackingToABuilding:
                break;
            case BuildingABarrack:
                System.out.println("fuck");
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
                    Barrack b = new Barrack(100,100);
                    this.currentBuilding = b;
                    this.getGamePanel().places.add(b);
                    new Thread(new ConstructBuildingS(this)).start();
                }
                break;
        }
    }

    @Override
    public void run() {

    }
}
class SAttack implements Runnable {
    Soldier soldier;

    public SAttack(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void run() {
        while (soldier.AttackingTo != null) {
            soldier.AttackingTo.processEvent(new SoldierAttackEvent(null, null));
            System.out.println(soldier.AttackingTo.life);
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class ConstructBuildingS implements Runnable{
    Soldier soldier;

    public ConstructBuildingS(Soldier soldier) {
        this.soldier = soldier;
    }

    @Override
    public void run() {
        while(soldier.currentBuilding != null){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            soldier.currentBuilding.processEvent(new ConstructPlaceEvent(soldier));

        }
    }
}
