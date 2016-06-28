package com.poorgroupproject.thrumania;

import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.form.*;
import com.poorgroupproject.thrumania.form.Frame;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.panel.GamePanel;
import com.poorgroupproject.thrumania.panel.PlayerPanel;
import com.poorgroupproject.thrumania.util.GameConfig;
import com.poorgroupproject.thrumania.util.ScreenRepainter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Thrumania {
    public static void main(String[] args) {
        GameConfig.setFrameDelayTime();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Frame f = new Frame();
                GamePanel g = new GamePanel(f.getWidth(),f.getHeight());
                ScreenRepainter repainter = new ScreenRepainter(g);
                repainter.start();
                f.add(g);
                f.showFrame();
                Thread screenRepainter = new ScreenRepainter(g);
                screenRepainter.start();
            }
        });

        Land.getInstance().loadMapImageFile();
        try {
            Land.getInstance().loadMap(new File("resource/map/map1.tmf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}