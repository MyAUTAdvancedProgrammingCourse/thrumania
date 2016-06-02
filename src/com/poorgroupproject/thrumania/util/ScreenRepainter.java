package com.poorgroupproject.thrumania.util;

import com.poorgroupproject.thrumania.form.Game;

/**
 * Created by ahmad on 5/30/16.
 */
public class ScreenRepainter extends Thread {
    private boolean runnable;
    private Game game;

    public ScreenRepainter(Game game){
        runnable = true;
        setDaemon(true);
        this.game = game;
    }
    @Override
    public void run() {
        while (runnable){




            try {
                Thread.sleep(GameConfig.frameDelayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
