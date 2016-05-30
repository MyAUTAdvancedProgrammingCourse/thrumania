package com.poorgroupproject.thrumania;

import com.poorgroupproject.thrumania.form.*;
import com.poorgroupproject.thrumania.util.GameConfig;

import java.awt.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Thrumania {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                com.poorgroupproject.thrumania.form.Frame f = new Game();
                f.showFrame();
                GameConfig.setFrameDelayTime();
                System.out.println(GameConfig.frameDelayTime);
            }
        });
    }
}
