package com.poorgroupproject.thrumania.pathfinder;
import  com.*;
import com.poorgroupproject.thrumania.item.GameObject;

/**
 * Created by Saman A.Mirhoseini on 26/06/2016.
 */
public interface MatrixMap {
    public boolean isbloacked(GameObject g, int x, int y);
    public float heur1(int x1,int y1,int x2,int y2);
    public int manhatanheur(int x1,int y1,int x2,int y2);
    public float distancebetweenCalc(int x1,int y1,int x2,int y2);
    public void gUpdate(int x,int y);
    public float Fcounter(int x,int y);
    public Path pathFinder();
    public boolean isValidTile(int x,int y);
}
