package com.poorgroupproject.thrumania.util;

import com.poorgroupproject.thrumania.form.Game;
import com.poorgroupproject.thrumania.panel.GamePanel;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class ScreenRepainter extends Thread {
    private boolean runnable;
    private GamePanel game;

    public ScreenRepainter(GamePanel game){
        runnable = true;
        setDaemon(true);
        this.game = game;
    }
    @Override
    public void run() {
        while (runnable){
            game.repaint();
            try {
                Thread.sleep(GameConfig.frameDelayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
