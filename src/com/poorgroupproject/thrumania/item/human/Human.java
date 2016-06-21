package com.poorgroupproject.thrumania.item.human;

import com.poorgroupproject.thrumania.item.GameObject;

import java.awt.*;

/**
 * Created by ahmad on 5/30/16.
 */
public abstract class Human extends GameObject implements Runnable{
    private final int HUMAN_WIDTH = 250;
    private final int HUMAN_HEIGHT = 250;

    public enum MovingSyle{
        WALKING, RUNNING
    };

    public MovingSyle movingSyle;

    public Human(int x, int y){
        super(x, y, 250, 250);
        movingSyle = MovingSyle.WALKING;
    }

}
