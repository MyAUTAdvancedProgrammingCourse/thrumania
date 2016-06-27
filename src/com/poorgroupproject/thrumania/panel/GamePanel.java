package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.events.ClickEvent;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Citizen;
import com.poorgroupproject.thrumania.item.place.Palace;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.util.GameConfig;
import com.poorgroupproject.thrumania.util.GameEngine;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class GamePanel extends GameEngine {

    private ArrayList<GameObject> gameObjects;
    private PlayerPanel playerPanel;

    private Rectangle mouseRectangleSelector;

    private enum MousePointerMode{NONE, PLAYER_PANEL_DRAGGING}

    private MousePointerMode mousePointerMode;
    private Point deltaMousePointerPositionToPlayerPanelForDraging;
    private Rectangle mousePosition;
    public GamePanel(int width, int height){
        initialize(width,height);
        gameObjects = new ArrayList<>();
        playerPanel = new PlayerPanel();
        mouseRectangleSelector = new Rectangle(0,0,0,0);
        mousePosition = new Rectangle(0,0,1,1);

        mousePointerMode = MousePointerMode.NONE;

        for (int i = 0; i < 18; i++) {
            gameObjects.add(new Palace(0,i * 100));
        }

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
                System.out.println("GamePanel.mouseDragged");
                if (mousePointerMode == MousePointerMode.NONE) {
                    mouseRectangleSelector.setSize(e.getX() - ((int) mouseRectangleSelector.getX()),
                            e.getY() - ((int) mouseRectangleSelector.getY()));
                }else if(mousePointerMode == MousePointerMode.PLAYER_PANEL_DRAGGING){
                    playerPanel.setLocation(new Point(((int) (e.getX() - deltaMousePointerPositionToPlayerPanelForDraging.getX())),
                            ((int) (e.getY() - deltaMousePointerPositionToPlayerPanelForDraging.getY()))));
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
                System.out.println("GamePanel.mouseClicked");
                Rectangle r = new Rectangle();
                r.setLocation(mouseEvent.getLocationOnScreen());
                r.setSize(new Dimension(1,1));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mousePosition.intersects(playerPanel.getBoundry())) {
                    mousePointerMode = MousePointerMode.PLAYER_PANEL_DRAGGING;
                    deltaMousePointerPositionToPlayerPanelForDraging = new Point(((int) (mouseEvent.getX() - playerPanel.getLocation().getX()))
                            , ((int) (mouseEvent.getY() - playerPanel.getLocation().getY())));
                }
                mouseRectangleSelector.setLocation(mouseEvent.getLocationOnScreen());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                System.out.println("GamePanel.mouseReleased");
                mouseRectangleSelector.setSize(0,0);
                mousePointerMode = MousePointerMode.NONE;
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
        Rectangle r = new Rectangle(0,0,1920,1080);
        drawOnFrame(Land.getInstance().getMapInBoundry(r),r);
        for (GameObject gameObj :
                gameObjects) {
            drawOnFrame(gameObj.getCurrentImage(), gameObj.getBoundry());
        }
        drawOnFrame(playerPanel.getView(),playerPanel.getBoundry());
        drawRectOnFrame(mouseRectangleSelector);
    }

//    @Override
//    public void paint(Graphics graphics) {
//        update(graphics);
//    }
//
//    public void update(Graphics graphics){
//        Graphics2D offScreenGraphic;
//        BufferedImage offScreen = ((BufferedImage) createImage(getWidth(), getHeight()));
//        offScreenGraphic = ((Graphics2D) offScreen.getGraphics());
//        for (GameObject gameObject :
//                gameObjects) {
//            offScreenGraphic.drawImage(gameObject.getCurrentImage(), gameObject.getX(), gameObject.getY(), null);
//        }
//
//        graphics.drawImage(offScreen, 0, 0, null);
//    }



}
