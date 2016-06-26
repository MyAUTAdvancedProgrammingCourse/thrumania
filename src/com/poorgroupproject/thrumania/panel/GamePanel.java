package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.events.ClickEvent;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Citizen;
import com.poorgroupproject.thrumania.item.place.Palace;
import com.poorgroupproject.thrumania.util.GameConfig;
import com.poorgroupproject.thrumania.util.GameEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    public GamePanel(int width, int height){
        initialize(width,height);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Palace(0,0));
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

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Rectangle r = new Rectangle();
                r.setLocation(mouseEvent.getLocationOnScreen());
                r.setSize(new Dimension(1,1));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

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
        for (GameObject gameObj :
                gameObjects) {
            drawOnFrame(gameObj.getCurrentImage(), gameObj.getBoundry());
        }
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
