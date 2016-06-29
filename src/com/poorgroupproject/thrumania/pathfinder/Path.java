package com.poorgroupproject.thrumania.pathfinder;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Saman A.Mirhoseini on 26/06/2016.
 */
public class Path {
     LinkedList<Pair> path = new LinkedList<Pair>();

    public void add_to_the_path(int x, int y) {
        path.add(new Pair(x, y));
    }
    public boolean contains(int x,int y){
        return path.contains(new Pair(x,y));
    }
    public boolean ReachedthePath(){
        return path.isEmpty();
    }
    public Pair getNextMove(){
        return path.pollLast();
    }
}
