package com.poorgroupproject.thrumania.item.human;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Saman A.Mirhoseini on 25/06/2016.
 */
public class ResourceCollocting extends TimerTask {
    Citizen citizen;


    public ResourceCollocting(Citizen citizen) {

        this.citizen = citizen;
    }

    @Override
    public void run() {
        switch (citizen.CurrentCell){
            case GOLD_MINE:
                citizen.Capacity += 5;
                citizen.amount_of_gold += 5;
                break;
            case IRON_MINE:
                citizen.Capacity += 4;
                citizen.amount_of_iron += 4;
                break;
            case TREE:
                citizen.Capacity += 3;
                citizen.amount_of_wood += 3;
                break;
        }
    }
}
