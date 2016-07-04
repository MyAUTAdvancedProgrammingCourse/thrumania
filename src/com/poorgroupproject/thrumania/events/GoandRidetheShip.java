package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.vehicle.TransportShip;

/**
 * Created by Saman A.Mirhoseini on 04/07/2016.
 */
public class GoandRidetheShip extends Event {
    TransportShip ship;

    public TransportShip getShip() {
        return ship;
    }

    public GoandRidetheShip(GameObject sender, TransportShip ship) {
        super(sender);

        this.ship = ship;
    }
}
