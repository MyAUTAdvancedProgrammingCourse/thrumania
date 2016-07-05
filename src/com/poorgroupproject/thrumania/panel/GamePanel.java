package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.Player.Player;
import com.poorgroupproject.thrumania.backgroundprocess.ThreadTicker;
import com.poorgroupproject.thrumania.events.*;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Citizen;
import com.poorgroupproject.thrumania.item.human.Human;
import com.poorgroupproject.thrumania.item.human.Oriention;
import com.poorgroupproject.thrumania.item.human.Soldier;
import com.poorgroupproject.thrumania.item.place.Barrack;
import com.poorgroupproject.thrumania.item.place.Palace;
import com.poorgroupproject.thrumania.item.place.Place;
import com.poorgroupproject.thrumania.item.place.WoodCutter;
import com.poorgroupproject.thrumania.item.vehicle.FishingShip;
import com.poorgroupproject.thrumania.item.vehicle.Ship;
import com.poorgroupproject.thrumania.item.vehicle.TransportShip;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Pair;
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
    Ai a = new Ai();

    private Rectangle mouseRectangleSelector;

    private ArrayList<GameObject> selectedObject;
    private enum MousePointerMode{NONE, PLAYER_PANEL_DRAGGING, MINIMAP_PANEL_DRAGGING};

    private enum MousePointerClickMode{NONE,ATTACK,BUILD_FARM};

    private MousePointerClickMode mousePointerClickMode;
    private MousePointerMode mousePointerMode;
    private Point deltaMousePointerPositionToPanelForDragging;
    private Rectangle mousePosition;
    public ArrayList<Place> places = new ArrayList<Place>();
    private GameObjectMenuPanel gameObjectMenuPanel;

    private GameObject targetObject;

    private Point viewPortPosition;

    private ThreadTicker ticker;

    private double zoomScale;


    public GamePanel(int width, int height){
        initialize(width,height);
        
        viewPortPosition = new Point(0,0);
        gameObjectMenuPanel = null;
        gameObjects = new ArrayList<>();
        selectedObject = new ArrayList<>();
        a.setGp(this);
        playerPanel = new PlayerPanel();
        miniMapPanel = new MiniMapPanel(this);

        mouseRectangleSelector = new Rectangle(0,0,0,0);
        mousePosition = new Rectangle(0,0,1,1);

        mousePointerMode = MousePointerMode.NONE;
        mousePointerClickMode = MousePointerClickMode.NONE;

//        Port p = new Port(100,100);
//        gameObjects.add(p);
        Player player = new Player("Yes");
        Player player1 = new Player("lol");
        Citizen c = new Citizen(1000,900,Oriention.Right);
        Citizen d = new Citizen(1600,800, Oriention.Down);
        Citizen h = new Citizen(1600,540,Oriention.UpRight);
        Citizen j = new Citizen(1400,540,Oriention.Right);
        Soldier s = new Soldier(1600,900,Oriention.UpRight);
        h.setPlayer(player1);
        j.setPlayer(player1);
        Palace palace = new Palace(1680,850);
        player.setPlayerPalace(palace);
        gameObjects.add(d);
        gameObjects.add(c);
        gameObjects.add(h);
        gameObjects.add(j);
        gameObjects.add(new FishingShip(730,860,Oriention.Down));
        gameObjects.add(new TransportShip(610,860,Oriention.Down));
        gameObjects.add(palace);
//        gameObjects.add(new Citizen(1080,800,Oriention.Down));
//        gameObjects.add(new Citizen(1700,630,Oriention.Up));
//        gameObjects.add(new Citizen(1700,700,Oriention.Up));
//        gameObjects.add(new Citizen(1600,650,Oriention.Up));
//        gameObjects.add(new Citizen(1500,800,Oriention.Up));
//        gameObjects.add(new Citizen(1400,950,Oriention.Up));
//        gameObjects.add(new Citizen(1600,800,Oriention.Up));
//        gameObjects.add(new Citizen(1200,650,Oriention.Up));
       // gameObjects.add(s);
        for(GameObject go:gameObjects){
            go.setPlayer(player);
        }
        for(GameObject go: gameObjects){
            go.setGamePanel(this);
        }
//        c.processEvent(new GoThePlaceEvent(null,new Pair(5,8)));
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
                if(keyEvent.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println(gameObjects.size());
                    gameObjects.get(1).processEvent(new GoandBuildAPlace(null,new Pair(7,9),new Barrack(0,0)));
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_R){
                    (new Thread(a)).start();
                }
                if(keyEvent.getKeyCode() == KeyEvent.VK_E) {
                    System.out.println(gameObjects.size());
                    gameObjects.get(1).processEvent(new GoandBuildAPlace(null,new Pair(5,10),new Barrack(0,0)));
                }
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);

                if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    moveUp();
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    moveDown();
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                    moveLeft();
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    moveRight();
                }

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
                    miniMapPanel.setLocation(new Point(((int) (e.getX() - deltaMousePointerPositionToPanelForDragging.getX())),
                            ((int) (e.getY() - deltaMousePointerPositionToPanelForDragging.getY()))));
                }else if(mousePointerMode == MousePointerMode.PLAYER_PANEL_DRAGGING){
                    playerPanel.setLocation(new Point(((int) (e.getX() - deltaMousePointerPositionToPanelForDragging.getX())),
                            ((int) (e.getY() - deltaMousePointerPositionToPanelForDragging.getY()))));
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
                Rectangle r = new Rectangle(mouseEvent.getLocationOnScreen(),new Dimension(1, 1));
                if (mouseEvent.getButton() == 1) {          // Left click
                    System.out.println("left click");
                    if (gameObjectMenuPanel != null){
                        if (gameObjectMenuPanel.getBoundry().intersects(r)){
                            int j = ((int) (mouseEvent.getY() - gameObjectMenuPanel.getBoundry().getY()));
                            String choice = gameObjectMenuPanel.getItem(j);
                            switch (choice){
                                case "Human - Attack":
                                    targetObject = gameObjectMenuPanel.getOwner();
                                    mousePointerClickMode = MousePointerClickMode.ATTACK;
                                    break;
                                case "Human - Build Barrack":
                                    break;


                            }
                            gameObjectMenuPanel = null;
                        }else {
                            gameObjectMenuPanel = null;
                        }

                    }else {

                        if (mousePointerClickMode == MousePointerClickMode.NONE) {
                            for (GameObject go :
                                    selectedObject) {
                                go.processEvent(new GoThePlaceEvent(null, GameObject.getLocationOnMatrix(mouseEvent.getX(), mouseEvent.getY())));
                            }

                            selectedObject = new ArrayList<GameObject>();
                        }else if (mousePointerClickMode == MousePointerClickMode.ATTACK){
                            int size = gameObjects.size();
                            for (int i = 0; i < size; i++) {
                                GameObject g = gameObjects.get(i);
                                if (g.getBoundry().intersects(r)) {
                                    switch (mousePointerClickMode) {
                                        case ATTACK:
                                            if (g instanceof Human) {
                                                targetObject.processEvent(new GoAndAttack(null, (Human) g));
                                                System.out.println(targetObject.getClass() +"   "+ g.getLocationOnMatrix().getX() +"  "+g.getLocationOnMatrix().getY() );
                                            }
                                            break;

                                    }
                                }
                            }
                        }

                    }

                }else if (mouseEvent.getButton() == 3){         //      Right click
                    int size = gameObjects.size();
                    for (int i = 0; i < size; i++) {
                        GameObject g = gameObjects.get(i);
                        if (g.getBoundry().intersects(r)) {
                            ArrayList<String> menuChoices = new ArrayList<String>();
                            if (g instanceof Human) {
                                menuChoices.add("Human - Attack");
                                menuChoices.add("Human - Build Barrack");
                                menuChoices.add("Human - Build Port");
                                menuChoices.add("Human - Build Farm");
                                gameObjectMenuPanel = new GameObjectMenuPanel(menuChoices,mouseEvent.getLocationOnScreen());
                                gameObjectMenuPanel.setOwner(g);
                            }else if (g instanceof Place){

                            }else if (g instanceof Ship){

                            }
                            break;
                        }
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mousePosition.intersects(playerPanel.getBoundry())) {
                    mousePointerMode = MousePointerMode.PLAYER_PANEL_DRAGGING;
                    deltaMousePointerPositionToPanelForDragging = new Point(((int) (mouseEvent.getX() - playerPanel.getLocation().getX()))
                            , ((int) (mouseEvent.getY() - playerPanel.getLocation().getY())));
                }else if (mousePosition.intersects(miniMapPanel.getBoundry())){
                    mousePointerMode = MousePointerMode.MINIMAP_PANEL_DRAGGING;
                    deltaMousePointerPositionToPanelForDragging = new Point(((int) (mouseEvent.getX() - miniMapPanel.getLocation().getX()))
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
                if (deltaMousePointerPositionToPanelForDragging != null)
                    deltaMousePointerPositionToPanelForDragging.setLocation(0,0);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    private void moveDown() {
        if (viewPortPosition.getY() + GameEngine.getScreenDimension().getHeight() < Land.getInstance().getMapHeight() - 10){
            viewPortPosition.setLocation(viewPortPosition.getX(),viewPortPosition.getY()  + 10);
            for (GameObject g : gameObjects) {
                g.translate(0, -10);
            }
        }
    }

    private void moveUp() {
        if (viewPortPosition.getY() > 0){
            viewPortPosition.setLocation(viewPortPosition.getX(),viewPortPosition.getY() - 10);
            for (GameObject g : gameObjects) {
                g.translate((0), 10);
            }
        }
    }

    private void moveRight() {
        System.out.println(Land.getInstance().getMapWidth());

        if (viewPortPosition.getX() + GameEngine.getScreenDimension().getWidth() < Land.getInstance().getMapWidth() - 10){
            viewPortPosition.setLocation(viewPortPosition.getX() + 10,viewPortPosition.getY());
            for (GameObject g : gameObjects) {
                g.translate(-10, 0);
            }
        }
    }
    private void moveLeft() {
        System.out.println(viewPortPosition);
        if (viewPortPosition.getX() > 0){
            System.out.println("kharrrrrrrrrrrrr");
            viewPortPosition.setLocation(viewPortPosition.getX() - 10,viewPortPosition.getY());
            for (GameObject g : gameObjects) {
                g.translate(10, 0);
            }
        }
    }
    @Override
    public synchronized void render() {
        Rectangle r = new Rectangle(((int) viewPortPosition.getX()), ((int) viewPortPosition.getY()), ((int) getScreenDimension().getWidth()), ((int) getScreenDimension().getHeight()));
        Rectangle khar = new Rectangle(0,0, ((int) GameEngine.getScreenDimension().getWidth()), ((int) GameEngine.getScreenDimension().getHeight()));
        drawOnFrame(Land.getInstance().getMapInBoundry(r),khar);
        for (GameObject gameObj :
                gameObjects) {
            drawOnFrame(gameObj.getCurrentImage(), gameObj.getBoundry());
        }
        for(Place p:this.places){
            try {
                drawOnFrame(p.getCurrentImage(), p.getBoundry());
            }
            catch(java.util.ConcurrentModificationException e){

            }
        }
        drawOnFrame(playerPanel.getView(),playerPanel.getBoundry());
        drawOnFrame(miniMapPanel.getView(),miniMapPanel.getBoundry());
        drawRectOnFrame(mouseRectangleSelector);
        if (gameObjectMenuPanel != null){
            drawOnFrame(gameObjectMenuPanel.getView(),gameObjectMenuPanel.getBoundry());
        }
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void setGameObjects(ArrayList<GameObject> gameObjects){
        this.gameObjects = gameObjects;
    }

    public synchronized void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }
}
