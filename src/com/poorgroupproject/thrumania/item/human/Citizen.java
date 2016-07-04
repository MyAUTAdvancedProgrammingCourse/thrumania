package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.place.Barrack;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.panel.GamePanel;
import com.poorgroupproject.thrumania.pathfinder.Cell;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.Path;
import com.poorgroupproject.thrumania.pathfinder.PathFinder;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author saman
 * @version 1.0.0
 */
public class Citizen extends Human {
    public Image[] images;
 //   public int life;
    private int speed;
    boolean alreadyStarted;
    int counter = 0;
    public boolean isCollectingResource;
    public Citizen(int x, int y,Oriention oriention) {
        super(x, y);
        this.life = 500;
        this.alreadyStarted = false;
        this.Capacity = 0;
        this.oriention = oriention;
        this.setCurrentImage(rightNow());
    }

    @Override
    public void loadResoure() {
        images = new Image[16];
        images[0] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top.gif");
        images[1] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_right.gif");
        images[2] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/right.gif");
        images[3] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_right.gif");
        images[4] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down.gif");
        images[5] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_left.gif");
        images[6] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/left.gif");
        images[7] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_left.gif");
        images[8] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/topStand.gif");
        images[9] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_rightStand.gif");
        images[10] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/rightStand.gif");
        images[11] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_rightStand.gif");
        images[12] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/downStand.gif");
        images[13] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_leftStand.gif");
        images[14] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/leftStand.gif");
        images[15] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_leftStand.gif");
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
        else if(event instanceof  SoldierAttackEvent){
            this.life -= 70;
            if(life <= 0){
                System.out.println(this.toString() + "   " + "dead");
                this.getGamePanel().getGameObjects().remove(this);
            }
        }
        else if(event instanceof GoThePlaceEvent){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
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
        else if(event instanceof  GoAndAttack){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
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
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
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
        else if(event instanceof GoandRidetheShip){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
            GoandRidetheShip gr = (GoandRidetheShip) event;
            currentTask = CurrentTask.RidingAShip;
            currentRidingShip = gr.getShip();
        }
        else if(event instanceof GetofTheship){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
            GetofTheship go = (GetofTheship) event;
            for(int i = -1;i < 2;i++){
                for(int j = -1;j < 2;j++){
                    if(i == 0 && j == 0)
                        continue;
                    if(Land.getInstance().getCells()[this.getLocationOnMatrix().getX()+i][this.getLocationOnMatrix().getY()+j] == Cell.PORT){
                        Pair temp = new Pair(this.getLocationOnMatrix().getX()+i,this.getLocationOnMatrix().getY()+j);
                        Pair temper = this.getCenterPosition(temp);
                        this.setX(temper.getX());
                        this.setY(temper.getY());
                    }
                }
            }
            currentTask = CurrentTask.StandingDoinfNothing;

        }
        else if (event instanceof GoAndCollectResourceEvent){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
             GoAndCollectResourceEvent ga = (GoAndCollectResourceEvent) event;
            currentTask = CurrentTask.ResourceCollecting;
            //currentBuilding = ga.place;
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
        else if(event instanceof GoBacktoYourPalace){
            alreadyStarted = false;
            AttackingTo = null;
            currentBuilding = null;
            currentMine = null;
            currentRidingShip = null;
            GoBacktoYourPalace ga = (GoBacktoYourPalace) event;
            currentTask = CurrentTask.GoingBacktoPalace;
            //currentBuilding = ga.place;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),
                    this.getLocationOnMatrix().getX(),
                    this.getLocationOnMatrix().getY(),
                    getPlayer().getPlayerPalace().getLocationOnMatrix().getX(),
                    getPlayer().getPlayerPalace().getLocationOnMatrix().getY(),
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
            case RidingAShip:
                return null;
            case StandingDoinfNothing:
                switch(oriention){
                    case Up:
                        return images[8];
                    case UpRight:
                        return images[9];
                    case Right:
                        return images[10];
                    case DownRight:
                        return images[11];
                    case Down:
                        return images[12];
                    case DownLeft:
                        return images[13];
                    case Left:
                        return images[14];
                    case UpLeft:
                        return images[15];
                }
                break;
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
        for(GameObject go:this.getGamePanel().getGameObjects()){
            if(go instanceof Human && go != this){
                if(Math.abs(go.getX() - this.getX()) + Math.abs(go.getY() - this.getY()) < 160 && ((go.getX() - this.getX()) + (go.getY() - this.getY())) != 0){
                    this.processEvent(new GoAndAttack(null,(Human)go));
                }
            }
        }
        switch(currentTask){
            case Moving:
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
                    if(!alreadyStarted) {
                        (new Thread(new CAttack(this))).start();
                        alreadyStarted = true;
                    }
                    if(AttackingTo.getLife() < 0){
                        alreadyStarted = false;
                        this.currentTask = null;
                    }

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
                    counter += 4;
                    counter = counter % 500;
                    if(counter > 400){
                        switch(map[temp.getX()][temp.getY()]){
                            case GOLD_MINE:
                                amount_of_gold += 1;
                                Capacity += 5;
                                break;
                            case IRON_MINE:
                                amount_of_iron += 1;
                                Capacity += 4;
                                break;
                            case FARM:
                                amount_of_food += 1;
                                break;
                            case TREE:
                                amount_of_wood +=1;
                                Capacity += 3;
                                break;
                            default:
                                currentTask = CurrentTask.StandingDoinfNothing;
                                break;
                        }
                    }
                    System.out.println(this.Capacity);
                    if(Capacity > 1000){
                        this.processEvent(new GoBacktoYourPalace(null));
                    }
                }
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
                    new Thread(new ConstructBuilding(this)).start();
                }
                break;
            case RidingAShip:
                this.setCurrentImage(rightNow());
                this.setX(currentRidingShip.getX());
                this.setY(currentRidingShip.getY());
                break;
            case GoingBacktoPalace:
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
                    this.getPlayer().setFood(getPlayer().getFood() + this.amount_of_food);
                    this.getPlayer().setGold(getPlayer().getGold() + this.amount_of_gold);
                    this.getPlayer().setIron(getPlayer().getIron() + this.amount_of_iron);
                    this.getPlayer().setWood(getPlayer().getWood() + this.amount_of_wood);
                    this.currentTask = CurrentTask.StandingDoinfNothing;
                }
                break;
        }
    }

    @Override
    public void run() {

    }
}
class CAttack implements Runnable {
    Citizen citizen;

    public CAttack(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public void run() {
        while (citizen.AttackingTo != null) {
            citizen.AttackingTo.processEvent(new CitizenAttackEvent(null, null));
            System.out.println(citizen.AttackingTo.life);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
class ConstructBuilding implements Runnable{
    Citizen citizen;

    public ConstructBuilding(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public void run() {
        while(citizen.currentBuilding != null){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            citizen.currentBuilding.processEvent(new ConstructPlaceEvent(citizen));

        }
    }
}


//class CitizenWalker extends TimerTask{
//
//    private Citizen citizen;
//
//    public CitizenWalker(Citizen citizen){
//        this.citizen = citizen;
//    }
//    @Override
//    public void run() {
//        citizen.nextImage();
//    }
//
//    private Image[] images;
//
//    private int imageIndex;
//
//    public Citizen(){
//        super(0,0);
//
//        images = new Image[16];
//        try {
//            for (int i = 0; i < images.length; i++) {
//                images[i] = ImageIO.read(new File("resource/image/item/woman/" + (i + 1) + ".png"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//        imageIndex = 0;
//        setCurrentImage(images[imageIndex]);
//        Timer t = new Timer();
//        TimerTask task = new CitizenWalker(this);
//        t.schedule(task,0,40);
//        (new Thread(this)).start();
//    }
//
//    @Override
//    public void run() {
//        while (true) {
//            int x = getX();
//            int y = getY();
//
//            setX(x + 10);
//            setY(y + 10);
//
//            try {
//                Thread.sleep(40);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void nextImage(){
//        imageIndex = (imageIndex + 1) % images.length;
//        setCurrentImage(images[imageIndex]);
//    }
//
//    @Override
//    public void processEvent(com.poorgroupproject.thrumania.events.Event event) {
//        if (event instanceof ClickEvent){
//            System.out.println("player clicked");
//        }
////    }

/////////////////////

//                }
//                else{
//                    if(stepWise >= 170 ){
//                        //ipdate////
//                        ////////
//                        ////
//                        System.out.println(currentPath.path.size());
//                        this.Updateoriention();
//                        this.setCurrentImage(rightNow());
//                        stepWise = 0;
////                        if(currentPath.ReachedthePath())
////                            currentTask = CurrentTask.StandingDoinfNothing;
//                    }
//                }
//                if(readyforReOriention(this.save)){
//                    System.out.println("hell yeah");
//                    System.out.println("size"+currentPath.path.size());
//                    this.Updateoriention();
//                    this.setCurrentImage(rightNow());
//                }
//                if(oriention == Oriention.Up || oriention == Oriention.Right|| oriention== Oriention.Down || oriention == Oriention.Left){