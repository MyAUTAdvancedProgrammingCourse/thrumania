package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.events.GoTargetEvent;
import com.poorgroupproject.thrumania.events.GoThePlaceEvent;
import com.poorgroupproject.thrumania.item.human.CurrentTask;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Pair;
import com.poorgroupproject.thrumania.pathfinder.PathFinder;
import com.poorgroupproject.thrumania.util.ResourcePath;

import java.awt.*;

/**
 * @author yahay
 * @version 1.0.0
 */
public class TransportShip  extends Ship {
    int targetX,targetY;
    Image[] images;
    public TransportShip(int x, int y) {
        super(x, y, 256, 256);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    setX(getX()+1);
                    setY(getY()+1);
                }

            }
        })).start();
        shipSpeed = 4;
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
        images[0] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tu.png");
        images[1] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tur.png");
        images[2] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tr.png");
        images[3] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tdr.png");
        images[4] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/td.png");
        images[5] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tdl.png");
        images[6] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tl.png");
        images[7] = Toolkit.getDefaultToolkit().getImage(ResourcePath.itemImagePath + "ship/tul.png");
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
                    this.setCurrentImage(rightNow());
                    stepWise = 0;
                    System.out.println(stepWise);

//                        if(currentPath.ReachedthePath())
//                            currentTask = CurrentTask.StandingDoinfNothing;
                }

                break;
        }
    }
}
