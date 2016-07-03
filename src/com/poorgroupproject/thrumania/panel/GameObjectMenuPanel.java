package com.poorgroupproject.thrumania.panel;

import java.awt.*;
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
        boundry = new Rectangle(0,0,PANEL_WIDTH,choices.size() * PANEL_CHOICE_HEIGHT);
        view = new BufferedImage(((int) boundry.getWidth()), ((int) boundry.getHeight()),BufferedImage.TYPE_INT_ARGB);
        drawItems(view.getGraphics());
    }

    private void drawItems(Graphics graphics){
        for (int i = 0; i < choices.size(); i++) {

        }

        }
    }
}
