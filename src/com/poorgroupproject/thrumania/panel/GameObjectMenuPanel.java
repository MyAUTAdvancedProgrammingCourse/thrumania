package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.item.GameObject;
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
    private final int PANEL_WIDTH = 300;
    private final int PANEL_CHOICE_HEIGHT = 30;
    private Rectangle boundry;

    private GameObject owner;

    public GameObjectMenuPanel(ArrayList<String> choices, Point location){
        this.choices = choices;
        int width = PANEL_WIDTH;
        int height = choices.size() * PANEL_CHOICE_HEIGHT;
        boundry = new Rectangle(((int) location.getX()) - width / 2, ((int) location.getY()) - height / 2,width,height);

        view = new BufferedImage(((int) boundry.getWidth()), ((int) boundry.getHeight()),BufferedImage.TYPE_INT_ARGB);
        drawItems(view.getGraphics());
    }

    private void drawItems(Graphics graphics){
        graphics.setFont(new Font("Arial", Font.PLAIN, 15));
        for (int i = 0; i < choices.size(); i++) {
            graphics.setColor(Color.BLUE);
            graphics.drawRect(0,i * PANEL_CHOICE_HEIGHT, PANEL_WIDTH, PANEL_CHOICE_HEIGHT);
            graphics.setColor(Color.RED);
            graphics.fillRect(0 + 1 ,i * PANEL_CHOICE_HEIGHT + 1, PANEL_WIDTH - 1, PANEL_CHOICE_HEIGHT - 1);
            graphics.setColor(Color.WHITE);
            graphics.drawString(choices.get(i), 0, (i + 1) * PANEL_CHOICE_HEIGHT - 10);
        }
    }

    public String getItem(int j){
        j /= PANEL_CHOICE_HEIGHT;
        return choices.get(j);
    }

    public Rectangle getBoundry(){
        return boundry;
    }

    public BufferedImage getView(){
        return view;
    }

    public void setOwner(GameObject gameObject){
        this.owner = gameObject;
    }

    public GameObject getOwner(){
        return owner;
    }
}

