package com.poorgroupproject.thrumania.pathfinder;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;
import com.poorgroupproject.thrumania.land.Land;

import java.lang.annotation.Target;
import java.nio.channels.Pipe;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Saman A.Mirhoseini on 26/06/2016.
 */
public class PathFinder implements MatrixMap{
    Pair currentPlace;
    Pair targetPlace;
    Path path;
    int maxWidth;
    int maxHeight;
    GameObject mover;
    public float gRightNow;
    LinkedList <Pair> closedSet = new LinkedList<Pair>();
    LinkedList <Pair> openSet = new LinkedList <Pair> ();

    Pair [][] Map;

    public PathFinder(Cell[][] map,int x1,int y1,int x2,int y2,GameObject g,int mapHeight,int mapWidth) {
        Map = new Pair[mapWidth][mapHeight];
      //  System.out.println(Map.length);
        this.maxWidth = mapWidth;
        this.maxHeight = mapHeight;
        this.mover = g;
        currentPlace = new Pair(x1,y1);
        targetPlace = new Pair(x2,y2);
      //  path = new Path();

        for(int i = 0; i < mapWidth;i++){
            for(int j = 0;j < mapHeight;j++){
             //   System.out.println(new Pair(i,j,map[i][j]) == null);
                Map[i][j] = new Pair(i,j,map[i][j]);
            }
        }
        //path = this.pathFinder();
    }



    @Override
    public boolean isbloacked(GameObject g, int x, int y) {
  //      System.out.print(Map[0][0] == null);
        if (isValidTile(x,y)){
//            if (g instanceof Ship) {
                if (Map[x][y].cellType == Cell.WATER) {
                    return true;
                }
                return false;
//            } else {
//                if (Map[x][y].cellType == Cell.WATER || Map[x][y].cellType == Cell.TREE)
//                    return true;
//                return false;
//            }
        }
        return true;
    }

    @Override
    public float heur1(int x1, int y1, int x2, int y2) {
        float a = targetPlace.x - currentPlace.x;
        float b = targetPlace.y - currentPlace.y;
        return (float)Math.sqrt((double)(a*a + b*b));
    }


    @Override
    public int manhatanheur(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    @Override
    public float distancebetweenCalc(int x1, int y1, int x2, int y2) {
        if(Math.abs(x2 - x1) + Math.abs(y2 - y1) == 2)
            return (float) 1.4;
        return (float) 1;
    }

    @Override
    public void gUpdate(int x, int y) {

    }

    @Override
    public float Fcounter(int x, int y) {

        return 0;
    }


    @Override
    public Path pathFinder() throws java.lang.ArrayIndexOutOfBoundsException{
        if(isbloacked(mover,targetPlace.x,targetPlace.y)) {
            return null;
        }
        Map[currentPlace.x][currentPlace.y].g = 0;
        Map[currentPlace.x][currentPlace.y].depth = 0;
        closedSet.clear();
        openSet.clear();
        openSet.add( Map[currentPlace.x][currentPlace.y]);
        Map[targetPlace.x][targetPlace.y].parent = null;
        int maxDistance = 0;
        while(maxDistance < 4000 && !openSet.isEmpty()){
            Pair current = openSet.get(0);
            if(current == Map[targetPlace.x][targetPlace.y]) {
                break;
            }
            openSet.remove(current);
           // System.out.println(openSet.contains(current));
            closedSet.add(current);
            for(int i = -1;i < 2;i++){
                for(int j = -1;j < 2;j++){
                    if(i == 0 && j == 0){
                        continue;
                    }
                    int checkinX = current.x + i;
                    int checkinY = current.y + j;
                    if(isValidTile(checkinX,checkinY) && !isbloacked(mover,checkinX,checkinY)){
                        float nextStepG = current.g + distancebetweenCalc(current.x,current.y,checkinX,checkinY);
                        Pair neighbor = Map[checkinX][checkinY];
                    //    System.out.println(neighbor.x);
                        if(nextStepG < neighbor.g){
                            if(openSet.contains(neighbor))
                                openSet.remove(neighbor);
                            if(closedSet.contains(neighbor))
                                closedSet.remove(neighbor);
                        }
                        if(!(openSet.contains(neighbor)) && !(closedSet.contains(neighbor))){
                            neighbor.g = nextStepG;
                            neighbor.heuristic = this.manhatanheur(checkinX,checkinY,targetPlace.x,targetPlace.y);
                            maxDistance = Math.max(maxDistance,neighbor.setParent(current));
                            if(neighbor == Map[targetPlace.x][targetPlace.y]) {
                                System.out.println("found");
                                System.out.println(neighbor.parent.x +" dds " + neighbor.parent.y);
                            }

                            openSet.add(neighbor);
                            Collections.sort(openSet);
                        }
                    }
                }
            }
        }
        if(Map[targetPlace.x][targetPlace.y].parent == null ) {
            System.out.println("imp");
            return null;
        }
        Path path = new Path();
        Pair target = Map[targetPlace.x][targetPlace.y];
        System.out.println(target.parent.x +"      "+target.parent.y);
        while(target != Map[currentPlace.x][currentPlace.y]){
            path.path.add(0,new Pair(target.x,target.y));
            target = target.parent;
        }
        path.path.add(0,new Pair(currentPlace.x,currentPlace.y));
        return path;
    }

    @Override
    public boolean isValidTile(int x, int y) {
        if(x < maxWidth && y < maxHeight && x >= 0 && y >= 0 )
            return true;
        return false;
    }

    public static  void main (String [] args){
        Cell [][] map = {
                {Cell.LAND,Cell.LAND,Cell.LAND,Cell.LAND},
                {Cell.LAND,Cell.WATER,Cell.WATER,Cell.WATER},
                {Cell.LAND,Cell.WATER,Cell.WATER,Cell.LAND},
                {Cell.LAND,Cell.LAND,Cell.LAND,Cell.LAND}
        };
        //System.out.print(map[1][1]);
        PathFinder pf = new PathFinder(map,0,1,3,3,null,4,4);
       // System.out.println(pf.closedSet.size());
        for(Pair p : pf.path.path){
            System.out.println(p.x +"  "+ p.y);
        }

    }
}
class LinkedListSorted extends LinkedList{

    public void addtoListSorted(Object o){
        this.add(o);
        Collections.sort(this);
    }
}
//if(isbloacked(this.mover,targetPlace.x,targetPlace.y))
//        return null;
//        Pair current;
//        int counter = 0;
//        currentPlace.g = 0;
//        openSet.clear();
//        openSet.addtoListSorted(currentPlace);
//        closedSet.clear();
//        while(!openSet.isEmpty() && counter < Math.abs((targetPlace.y - currentPlace.y) * (targetPlace.x - currentPlace.x))){
//        current = (Pair) openSet.get(0);
//        if(current.equals(targetPlace))
//        break;
//        openSet.remove(current);
//        closedSet.add(current);
//        for(int i = -1;i < 2;i++){
//        for(int j = -1;j < 2;j++){
//        if(i == 0 && j == 0)
//        continue;
//        if(current.x + i > 3 || current.y + j > 3 || current.x + i <0 || current.y + j < 0){
//        continue;
//        }
//        Pair neighbor;
//        if(!isbloacked(mover,current.x+i,current.y+j) && !closedSet.contains(new Pair(current.x+i,current.y+j)) && !openSet.contains(new Pair(current.x+i,current.y+j))){
//        neighbor = new Pair(current.x+i,current.y+j);
//        neighbor.g = current.g + distancebetweenCalc(current.x,current.y,neighbor.x,neighbor.y);
//        neighbor.heuristic = manhatanheur(neighbor.x,neighbor.y,targetPlace.x,targetPlace.y);
//        neighbor.parent = current;
//        }
//        else if(openSet.contains(new Pair(current.x+i,current.y+j))){
//        neighbor = new Pair(current.x+i,current.y+j);
//        neighbor.g = current.g + distancebetweenCalc(current.x,current.y,neighbor.x,neighbor.y);
//        neighbor.heuristic = manhatanheur(neighbor.x,neighbor.y,targetPlace.x,targetPlace.y);
//        Pair temp = (Pair) openSet.get(openSet.indexOf(new Pair(current.x+i,current.y+j)));
//        if(temp.compareTo(neighbor) == 1){
//        openSet.remove(temp);
//        openSet.addtoListSorted(neighbor);
//        neighbor.parent = current;
//        }
//        /////////////////
//        }
//        }
//        }
//        }
//        Path pa = new Path();
//        pa.path = closedSet;
//        return pa;
//
////   return null;