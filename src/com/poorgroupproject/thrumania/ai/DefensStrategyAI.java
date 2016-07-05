package com.poorgroupproject.thrumania.ai;

/**
 * Created by Asus on 7/4/2016.
 */
public class DefensStrategyAI extends ThrumaniaAI {

    int actionNum;

    public DefensStrategyAI(Player player) {
        super(player);
        start();
    }

    @Override
    public void run() {
        while (true){
            actionNum = makeRandNum();
            if(actionNum<=2 && player.getFood() >= 2000 && player.getIron() >= 500 && player.getGold() >= 250 && player.getWood() >= 600){
                makeSoldier();
            }
            if (actionNum <=2 && actionNum >= 4 && player.getGold() >= 100 && player.getIron() >= 150 && player.getWood() >= 200) {
                constructBarracks();
            }
            if (actionNum >= 4 && actionNum <= 24 && player.getFood() >= 1000) {
                makeHuman();
            }
            if (actionNum <= 24 && actionNum >= 34 && player.getGold() >= 250 && player.getWood() >= 800 & player.getIron() >= 500) {
                sendToConstructPort();
            }
            if (actionNum >= 34 && actionNum <= 35 && player.getGold() >= 250 && player.getIron() >= 1000 && player.getWood() >= 400) {
                sendToconstructTransportShip();
            }
            if (actionNum >= 35 && actionNum <= 50 && player.getGold() >= 250 && player.getWood() >= 2000 && player.getIron() >= 500) {
                sendToconstructFishingShip();
            }
            if (actionNum >= 50 && actionNum <= 70) {
                sendToGoldMine();
            }
            if (actionNum >= 70 && actionNum <= 85) {
                sendToWood();
            }
            if (actionNum >= 85 && actionNum <= 100) {
                sendToIronMine();
            }

        }

    }
}
