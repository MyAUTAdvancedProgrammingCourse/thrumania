package com.poorgroupproject.thrumania.util;

/**
 * Created by ahmad on 5/30/16.
 */
public class GameConfig {
    public static int fps = 30;
    public static long frameDelayTime;
    public static void setFrameDelayTime(){
        frameDelayTime = 1000 / fps;
    }
}
