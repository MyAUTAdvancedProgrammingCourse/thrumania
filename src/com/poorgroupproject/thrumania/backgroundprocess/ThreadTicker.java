package com.poorgroupproject.thrumania.backgroundprocess;

import com.poorgroupproject.thrumania.item.GameObject;

import java.util.ArrayList;

/**
 * Created by ahmad on 6/30/16.
 */
public class ThreadTicker extends Thread {
    private int tps = 30;
    private double delayTickInterval;
    private ArrayList<GameObject> gameObjects;
    public ThreadTicker(ArrayList<GameObject> gameObjects){
        this.gameObjects = gameObjects;
        setDaemon(true);
        delayTickInterval = 1000 / tps;
    }

    @Override
    public void run() {
        while (true){
            for (GameObject go :
                    gameObjects) {
                go.tik();
            }
            try {
                Thread.sleep((long) delayTickInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
