package com.poorgroupproject.thrumania.util;

/**
 * Created by ahmad on 5/30/16.
 */
public class ScreenRepainter extends Thread {
    private boolean runnable;
    public ScreenRepainter(){
        runnable = true;
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
