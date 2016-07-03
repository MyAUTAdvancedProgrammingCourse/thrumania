package com.poorgroupproject.thrumania.Player;

import com.poorgroupproject.thrumania.item.GameObject;

import java.util.ArrayList;

/**
 * Created by Saman A.Mirhoseini on 03/07/2016.
 */
public class Player {


    int wood;
    int iron;
    int gold;
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

    int food;
    int citizenNum;
    int soldierNum;
    String name;
    ArrayList <GameObject> gameobject;

    public Player(String name) {
        this.gameobject = gameobject;
        this.name = name;
        wood = 0;
        gold= 0;
        iron = 0;
        food = 0;
        citizenNum = 0;
        soldierNum = 0;
    }
}
