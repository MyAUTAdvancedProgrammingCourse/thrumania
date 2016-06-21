package com.poorgroupproject.thrumania.form;

import com.poorgroupproject.thrumania.panel.GamePanel;
import com.poorgroupproject.thrumania.util.ScreenRepainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Frame extends JFrame{
    private Dimension screenDimension;
    private Dimension mousePointerDimension;

    private GraphicsDevice device;

    public Frame(){
        setUpGraphicFullscreen();
        setLayout(null);
        setTitle("Thrumania");
        setMouseCursor();
        keyListener();
    }

    private void setUpGraphicFullscreen(){
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        //setUndecorated(true);
        device.setFullScreenWindow(this);
    }
    private void setMouseCursor(){
        mousePointerDimension = new Dimension(60,60);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("resource/image/cursor/default.png");
        image = image.getScaledInstance((int)mousePointerDimension.getWidth(),(int)mousePointerDimension.getHeight(),Image.SCALE_DEFAULT);
        /**
         * ***********IMPORTANT***********
         * Change the (20,0) point if the mouse cursor change. This is the anchor point.
         */
        Cursor c = toolkit.createCustomCursor(image , new Point(14, 0), "img");
        this.setCursor (c);
    }

    private void keyListener(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                    System.out.println("some text");
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    public void showFrame(){
        setVisible(true);
    }

    //public abstract void putElements();

    public Dimension getScreenDimension(){
        return screenDimension;
    }
}
