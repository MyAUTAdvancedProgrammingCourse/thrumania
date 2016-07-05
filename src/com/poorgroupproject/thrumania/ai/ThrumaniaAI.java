package com.poorgroupproject.thrumania.ai;

import com.poorgroupproject.thrumania.panel.GamePanel;

/**
 * Created by Asus on 7/3/2016.
 */
public abstract class ThrumaniaAI extends Thread{

    Player player;
    GamePanel gamePanel;

    public ThrumaniaAI(Player player, GamePanel gamePanel){
        this.player=player;
        this.gamePanel = gamePanel;
    }



    public void makeHuman(){
        //send event to make human

    }
    public void makeSoldier(){
        //send event to make soldier
        System.out.println("make soldier");
    }
    public void sendToGoldMine(){
        //send people to gold mine
        System.out.println("send to gold mine");
    }
    public void sendToIronMine(){
        //send people to iron mind
        System.out.println("sent to iron");
    }
    public void sendToWood(){
        //send people to make wood
        System.out.println("send to wood");
    }
    public void sendToConstructPort(){
        System.out.println("port");

    }
    public void sendToconstructFishingShip(){
        System.out.println("fish");

    }
    public void sendToconstructTransportShip(){
        System.out.println("transport");

    }
    public void attack(){
        System.out.println("attack");

    }
    public void constructBarracks(){
        System.out.println("barracks");

    }

    public int makeRandNum() {
        double num;
        int randnum;
        num = Math.random();
        randnum=(int)(num*100);
        return randnum;
    }

    @Override
    public abstract void run();

}
