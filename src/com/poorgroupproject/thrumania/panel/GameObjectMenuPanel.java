package com.poorgroupproject.thrumania.panel;

import javafx.scene.paint.*;

import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class GameObjectMenuPanel {
    private BufferedImage view;
    private ArrayList<String> choices;
    private final int PANEL_WIDTH = 100;
    private final int PANEL_CHOICE_HEIGHT = 30;
    private Rectangle boundry;


    public GameObjectMenuPanel(ArrayList<String> choices){
        this.choices = choices;
        boundry = new Rectangle(800,800,PANEL_WIDTH,choices.size() * PANEL_CHOICE_HEIGHT);
        view = new BufferedImage(((int) boundry.getWidth()), ((int) boundry.getHeight()),BufferedImage.TYPE_INT_ARGB);
        drawItems(view.getGraphics());
    }

    private void drawItems(Graphics graphics){
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        for (int i = 0; i < choices.size(); i++) {
            graphics.setColor(Color.BLUE);
            graphics.drawRect(0,i * PANEL_CHOICE_HEIGHT, PANEL_WIDTH, PANEL_CHOICE_HEIGHT);
            graphics.setColor(Color.RED);
            graphics.fillRect(0 + 1 ,i * PANEL_CHOICE_HEIGHT + 1, PANEL_WIDTH - 1, PANEL_CHOICE_HEIGHT - 1);
            graphics.setColor(Color.WHITE);
            graphics.drawString(choices.get(i), 0, i * PANEL_CHOICE_HEIGHT);
            System.out.println(choices.get(i));
        }
    }

    public Rectangle getBoundry(){
        return boundry;
    }

    public BufferedImage getView(){
        return view;
    }
}
