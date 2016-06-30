package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.backgroundprocess.ThreadTicker;
import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Citizen;
import com.poorgroupproject.thrumania.item.human.Oriention;
import com.poorgroupproject.thrumania.item.place.Palace;
import com.poorgroupproject.thrumania.item.place.Port;
import com.poorgroupproject.thrumania.item.vehicle.FishingShip;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.util.GameConfig;
import com.poorgroupproject.thrumania.util.GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class GamePanel extends GameEngine {

    private ArrayList<GameObject> gameObjects;
    private PlayerPanel playerPanel;
    private MiniMapPanel miniMapPanel;

    private Rectangle mouseRectangleSelector;

    private ArrayList<GameObject> selectedObject;
    private enum MousePointerMode{NONE, PLAYER_PANEL_DRAGGING, MINIMAP_PANEL_DRAGGING}

    private MousePointerMode mousePointerMode;
    private Point deltaMousePointerPositionToPanelForDraging;
    private Rectangle mousePosition;


    private ThreadTicker ticker;
    public GamePanel(int width, int height){
        initialize(width,height);
        gameObjects = new ArrayList<>();
        selectedObject = new ArrayList<>();

        playerPanel = new PlayerPanel();
        miniMapPanel = new MiniMapPanel();

        mouseRectangleSelector = new Rectangle(0,0,0,0);
        mousePosition = new Rectangle(0,0,1,1);

        mousePointerMode = MousePointerMode.NONE;

//        Port p = new Port(100,100);
//        gameObjects.add(p);
        gameObjects.add(new Citizen(130,330, Oriention.Down));


        ticker = new ThreadTicker(gameObjects);
        ticker.start();

        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(GameConfig.frameDelayTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();

        addEventListener();

    }

    private void initialize(int width, int height){
        setSize(width,height);
        setLocation(0,0);
    }

    private void addEventListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mousePointerMode == MousePointerMode.NONE) {
                    mouseRectangleSelector.setSize(e.getX() - ((int) mouseRectangleSelector.getX()),
                            e.getY() - ((int) mouseRectangleSelector.getY()));
                }else if (mousePointerMode == MousePointerMode.MINIMAP_PANEL_DRAGGING){
                    miniMapPanel.setLocation(new Point(((int) (e.getX() - deltaMousePointerPositionToPanelForDraging.getX())),
                            ((int) (e.getY() - deltaMousePointerPositionToPanelForDraging.getY()))));
                }else if(mousePointerMode == MousePointerMode.PLAYER_PANEL_DRAGGING){
                    playerPanel.setLocation(new Point(((int) (e.getX() - deltaMousePointerPositionToPanelForDraging.getX())),
                            ((int) (e.getY() - deltaMousePointerPositionToPanelForDraging.getY()))));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePosition.setLocation(e.getLocationOnScreen());
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Rectangle r = new Rectangle();
                r.setLocation(mouseEvent.getLocationOnScreen());
                r.setSize(new Dimension(1,1));
                for (GameObject go :
                        selectedObject) {
                    go.processEvent(new GoThePlaceEvent(null,GameObject.getLocationOnMatrix(mouseEvent.getX(), mouseEvent.getY())));
                }

                selectedObject = new ArrayList<GameObject>();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mousePosition.intersects(playerPanel.getBoundry())) {
                    mousePointerMode = MousePointerMode.PLAYER_PANEL_DRAGGING;
                    deltaMousePointerPositionToPanelForDraging = new Point(((int) (mouseEvent.getX() - playerPanel.getLocation().getX()))
                            , ((int) (mouseEvent.getY() - playerPanel.getLocation().getY())));
                }else if (mousePosition.intersects(miniMapPanel.getBoundry())){
                    mousePointerMode = MousePointerMode.MINIMAP_PANEL_DRAGGING;
                    deltaMousePointerPositionToPanelForDraging = new Point(((int) (mouseEvent.getX() - miniMapPanel.getLocation().getX()))
                            , ((int) (mouseEvent.getY() - miniMapPanel.getLocation().getY())));
                }else if (mousePointerMode == MousePointerMode.NONE) {
                    mouseRectangleSelector.setLocation(mouseEvent.getLocationOnScreen());
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (mousePointerMode == MousePointerMode.NONE) {
                    for (GameObject go :
                            gameObjects) {
                        if (mouseRectangleSelector.intersects(go.getBoundry())) {
                            selectedObject.add(go);
                            go.makeSelected();
                        }
                    }
                    mouseRectangleSelector.setSize(0, 0);
                }
                mousePointerMode = MousePointerMode.NONE;
                if (deltaMousePointerPositionToPanelForDraging != null)
                    deltaMousePointerPositionToPanelForDraging.setLocation(0,0);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    @Override
    public void render() {
        Rectangle r = new Rectangle(0,0, ((int) getScreenDimension().getWidth()), ((int) getScreenDimension().getHeight()));
        drawOnFrame(Land.getInstance().getMapInBoundry(r),r);
        for (GameObject gameObj :
                gameObjects) {
            drawOnFrame(gameObj.getCurrentImage(), gameObj.getBoundry());
        }
        drawOnFrame(playerPanel.getView(),playerPanel.getBoundry());
        drawOnFrame(miniMapPanel.getView(),miniMapPanel.getBoundry());
        drawRectOnFrame(mouseRectangleSelector);
    }
}
