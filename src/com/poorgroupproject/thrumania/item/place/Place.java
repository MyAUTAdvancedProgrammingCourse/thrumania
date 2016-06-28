package com.poorgroupproject.thrumania.item.place;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;

import java.util.ArrayList;

/**
 * @author amin
 * @version 1.0.0
 */
public abstract class Place extends GameObject {
    private int toughness;
    private int availabeGold;
    private int availabeIron;
    private int availabeFood;
    private int availabeWood;

    /**
     * Constructor
     * @param x
     * @param y
     */
    protected Place(int x, int y) {
        super(x, y, 100,100);
        availabeGold = 0;
        availabeIron = 0;
        availabeFood = 0;
        availabeWood = 0;

    }

    protected int getToughness() {
        return toughness;
    }
    protected void setToughness(int toughness){
        this.toughness = toughness;
    }
    public int getAvailabeWood() {
        return availabeWood;
    }

    public void setAvailabeWood(int availabeWood) {
        this.availabeWood = availabeWood;
    }

    public int getAvailabeGold() {
        return availabeGold;
    }

    public void setAvailabeGold(int availabeGold) {
        this.availabeGold = availabeGold;
    }

    public int getAvailabeIron() {
        return availabeIron;
    }

    public void setAvailabeIron(int availabeIron) {
        this.availabeIron = availabeIron;
    }

    public int getAvailabeFood() {
        return availabeFood;
    }

    public void setAvailabeFood(int availabeFood) {
        this.availabeFood = availabeFood;
    }

}
