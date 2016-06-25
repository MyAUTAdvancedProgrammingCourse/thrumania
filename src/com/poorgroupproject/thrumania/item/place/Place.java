package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;

import java.util.ArrayList;

/**
 * Created by Amin on 6/21/2016.
 */
public abstract class Place extends GameObject {
    private int toughness;
    private int numberOfWorkingHuman;
    private ArrayList<Human> humanArrayList = new ArrayList<>();

    private Place(int toughness) {
        this.toughness = toughness;
    }

    public int getToughness() {
        return toughness;
    }

    public int getNumberOfWorkingHuman() {
        return numberOfWorkingHuman;
    }

    public ArrayList<Human> getHumanArrayList() {
        return humanArrayList;
    }

    public void setHumanArrayList(ArrayList<Human> humanArrayList) {
        this.humanArrayList = humanArrayList;
    }
}
