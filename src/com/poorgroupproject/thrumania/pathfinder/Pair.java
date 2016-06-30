package com.poorgroupproject.thrumania.pathfinder;

import com.poorgroupproject.thrumania.pathfinder.Cell;

/**
 * Created by Saman A.Mirhoseini on 26/06/2016.
 */
public class Pair implements Comparable {
    Cell cellType;
    int x;
    int y;
    float heuristic;
    float g;
    float f;
    int depth;
    Pair parent;
    public Pair(int x,int y,Cell cell) {
        this.x = x;
        this.y = y;
        cellType = cell;
        //(new Pair(0,0)).equals()
    }
    public Pair(int x,int y){
        this(x,y,null);
    }
//    @Override
//    public boolean equals(Object o){
//        Pair p = (Pair) o;
//        if(x == p.x && y == p.y){
//            return true;
//        }
//        return false;
//    }
    public int setParent(Pair parent) {
        depth = parent.depth + 1;
        this.parent = parent;

        return depth;
    }

    @Override
    public int compareTo(Object o) {
        Pair p = (Pair) o;
        float fprime = p.g + p.heuristic;
        f = g + heuristic;
        if(f > fprime)
            return 1;
        else if(f < fprime)
            return -1;
        return 0;
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
