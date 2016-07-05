package com.poorgroupproject.thrumania;

import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.panel.GamePanel;
import com.poorgroupproject.thrumania.panel.NetworkSettingPanel;
import com.poorgroupproject.thrumania.util.GameEngine;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Ahmad on 7/3/2016.
 */
public class ThrumaniaNetwork {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Land.getInstance().setGamePanel(new GamePanel(((int) GameEngine.getScreenDimension().getWidth()), ((int) GameEngine.getScreenDimension().getHeight())));
                try {
                    Land.getInstance().loadMap(new File("resource/map/map1.tmf"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                NetworkSettingPanel n = new NetworkSettingPanel();
                n.setVisible(true);
            }
        });
    }
}
