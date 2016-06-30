package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.land.Land;
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
    public boolean isCollectingResource;
    public Citizen(int x, int y) {
        super(x, y);
        this.life = 300;
        this.Capacity = 0;
    }

    @Override
    public void loadResoure() {
        images = new Image[8];
        images[0] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top.gif");
        images[1] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_right.gif");
        images[2] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/right.gif");
        images[3] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_right.gif");
        images[4] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down.gif");
        images[5] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/down_left.gif");
        images[6] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/left.gif");
        images[7] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "human/top_left.gif");
    }

    @Override
    public void processEvent(Event event) {
        if(event instanceof CitizenAttackEvent){
            this.life -= 20;
        }
        else if(event instanceof  SoldierAttackEvent){
            this.life -= 70;
        }
        else if(event instanceof GoThePlaceEvent){
            GoThePlaceEvent gt = (GoThePlaceEvent) event;
            PathFinder pf = new PathFinder(Land.getInstance().getCells(),this.getLocationOnMatrix().getX(),this.getLocationOnMatrix().getY(),gt.targetX,gt.targetY,new Citizen(0,0),100,100);
            PathfindingRunnable pfr = new PathfindingRunnable(pf);
            (new Thread(pfr)).start();
            currentPath = pfr.path;
            currentTask = CurrentTask.Moving;
            oriention = DefineOreintion(this.getLocationOnMatrix(),currentPath.getNextMove());
            stepWise = 0;
            this.setCurrentImage(rightNow());
        }
    }
    private Image rightNow(){
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
////////////////////////////////////////
    //////////
    ////
    @Override
    public void tik() {
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
                if(oriention == Oriention.Up || oriention == Oriention.Right|| oriention== Oriention.Down || oriention == Oriention.Left){
                    if(stepWise >= 120 && !currentPath.ReachedthePath()){
                        this.Updateoriention();
                        stepWise = 0;
                    }
                }
                else{
                    if(stepWise >= 180 && !currentPath.ReachedthePath()){
                        //ipdate////
                        ////////
                        ////
                        this.Updateoriention();
                        stepWise = 0;
                    }
                }

                break;
        }
    }

    @Override
    public void run() {

    }
}
class CAttack extends TimerTask{
    Citizen citizen;
    Human target;

    public CAttack(Citizen citizen,Human target) {
        this.citizen = citizen;
        this.target = target;
    }

    @Override
    public void run() {

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
//    }