package com.poorgroupproject.thrumania.item.vehicle;

import com.poorgroupproject.thrumania.events.ClickEvent;
import com.poorgroupproject.thrumania.events.Event;
import com.poorgroupproject.thrumania.events.StopFishingEvent;
import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Citizen;
import com.poorgroupproject.thrumania.item.human.Human;
import com.poorgroupproject.thrumania.item.place.Port;

import java.util.ArrayList;

/**
 *@author yahya
 * @version 1.0.0
 */
public class FishingShip extends Ship {

    Port port;
    int peopleNum;
    final int peopleCapacity=1200;
    int foodNum=0;

    int shipCapacity;
    Human []fisherMan;



    public FishingShip(Port port ,int x, int y, int width, int height) {
        super(x, y, width, height);
        this.port=port;
        fisherMan =new Human[7];
    }



    public void getHumqn(){
        for(int i=0;i<7;i++){
           // fisherMan[i]=pickUpHuman;
        }
    }

    public void IsEmpty(){
       shipCapacity= peopleNum *peopleCapacity;
        if(foodNum==shipCapacity){
            //send event to people stop fishing
            //chang the image
            Human h =new Citizen(0,0);
            h.processEvent(new StopFishingEvent(this));


        }
    }

    @Override
    public void loadResoure() {

    }

    @Override
    public void processEvent(Event event) {


    }

    public void setPort(Port port) {
        this.port = port;
    }
}
