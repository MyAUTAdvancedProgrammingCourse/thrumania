package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.events.GoAndAttack;
import com.poorgroupproject.thrumania.events.GoAndCollectResourceEvent;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;
import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.pathfinder.Cell;
import com.poorgroupproject.thrumania.pathfinder.Pair;

import java.util.Vector;

/**
 * Created by Saman A.Mirhoseini on 05/07/2016.
 */
public class Ai implements Runnable {
    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public GamePanel gp;
    Cell[][] map;
    Vector<Pair> resources = new Vector<Pair>();
    public Ai() {
    map = Land.getInstance().getCells();
        for(int i = 0;i < Land.getInstance().getRows();i++){
            for(int j = 0;j < Land.getInstance().getCols() ;j++){
                if(map[i][j] == Cell.GOLD_MINE || map[i][j] == Cell.IRON_MINE ){
                    resources.add(new Pair(i,j));
                }
            }
        }
    }

    @Override
    public void run() {
        //while(true){
        gp.getGameObjects().get(0).processEvent(new GoAndAttack(null,(Human)gp.getGameObjects().get(6)));

    }
}
