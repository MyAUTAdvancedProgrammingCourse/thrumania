package com.poorgroupproject.thrumania.ai;

import com.poorgroupproject.thrumania.panel.GamePanel;

/**
 * Created by Asus on 7/3/2016.
 */
public class AttackStrategyAI extends ThrumaniaAI {
    int actionNum;
    public AttackStrategyAI(Player player, GamePanel gamePanel){
        super(player, gamePanel);
        start();
    }

    @Override
    public void run() {
        while(true) {
            actionNum = makeRandNum();
            System.out.println(actionNum);
            if (actionNum <= 20 && player.getFood() >= 2000 && player.getIron() >= 500 && player.getGold() >= 250 && player.getWood() >= 600) {
                makeSoldier();
            }
            if (actionNum <= 20 && actionNum >= 25 && player.getGold() >= 100 && player.getIron() >= 150 && player.getWood() >= 200) {
                constructBarracks();
            }
            if (actionNum >= 25 && actionNum <= 30 && player.getFood() >= 1000) {
                makeHuman();
            }
            if (actionNum <= 35 && actionNum >= 30 && player.getGold() >= 250 && player.getWood() >= 800 & player.getIron() >= 500) {
                sendToConstructPort();
            }
            if (actionNum >= 35 && actionNum <= 50 && player.getGold() >= 250 && player.getIron() >= 1000 && player.getWood() >= 400) {
                sendToconstructTransportShip();
            }
            if (actionNum >= 50 && actionNum <= 60 && player.getGold() >= 250 && player.getWood() >= 2000 && player.getIron() >= 500) {
                sendToconstructFishingShip();
            }
            if (actionNum >= 60 && actionNum <= 70) {
                sendToGoldMine();
            }
            if (actionNum >= 70 && actionNum <= 80) {
                sendToWood();
            }
            if (actionNum >= 80 && actionNum <= 90) {
                sendToIronMine();
            }
            if (actionNum >= 90 && actionNum <= 100 && player.getSoldierNum()>=30) {
                attack();
            }
        }
    }
}
