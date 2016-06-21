package com.poorgroupproject.thrumania.util;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class GameConfig {
    public static int fps = 60;
    public static long frameDelayTime;
    public static void setFrameDelayTime(){
        frameDelayTime = 1000 / fps;
    }
}
