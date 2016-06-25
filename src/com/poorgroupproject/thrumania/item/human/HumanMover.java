package com.poorgroupproject.thrumania.item.human;

import java.util.TimerTask;

/**
 * Created by Saman A.Mirhoseini on 25/06/2016.
 */
public class HumanMover extends TimerTask {
    Human human;

    public HumanMover(Human human) {
        this.human = human;
    }

    @Override
    public void run() {
        switch (human.oriention){
            case Up:
                human.moveUp();
                break;
            case UpRight:
                human.moveUpRight();
                break;
            case Right:
                human.moveRight();
                break;
            case DownRight:
                human.moveDownRight();
                break;
            case Down:
                human.moveDown();
                break;
            case DownLeft:
                human.moveDownLeft();
                break;
            case Left:
                human.moveLeft();
                break;
            case UpLeft:
                human.moveUpLeft();
                break;
        }
    }
}
