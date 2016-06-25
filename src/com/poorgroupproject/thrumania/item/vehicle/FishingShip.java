package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.place.Port;

/**
 *@author yahya
 * @version 1.0.0
 */
public class FishingShip extends Ship {

    Port port;
    int peopleNum;
    final int peopleCapacity=100;
    int foodNum=0;

    int shipCapacity;
    public FishingShip(Port port ,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.port=port;
    }

    public void IsEmpty(){
       shipCapacity= peopleNum *peopleCapacity;
        if(foodNum==shipCapacity){
            //send event to people stop fishing
            //chang the image


        }
    }

    @Override
    public void loadResoure() {

    }

    @Override
    public void processEvent(Event event) {

    }
}
