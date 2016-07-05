package com.poorgroupproject.thrumania.ai;



/**
 * Created by Saman A.Mirhoseini on 03/07/2016.
 */
public class Player {


    int wood=2000;
    int iron=2000;
    int gold=2000;

    public void setCitizenNum(int citizenNum) {
        this.citizenNum = citizenNum;
    }

    public void setSoldierNum(int soldierNum) {
        this.soldierNum = soldierNum;
    }

    public int getCitizenNum() {

        return citizenNum;
    }

    public int getSoldierNum() {
        return soldierNum;
    }

    public int getWood() {
        return wood;
    }

    public int getIron() {
        return iron;
    }

    public int getGold() {
        return gold;
    }

    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    int food=2000;
    int citizenNum;
    int soldierNum=0;
    String name;
   // ArrayList <GameObject> gameobject;

    public Player(String name) {
       // this.gameobject = gameobject;
        this.name = name;
        wood = 0;
        gold= 0;
        iron = 0;
        food = 0;
        citizenNum = 0;
        soldierNum = 0;
    }
}
