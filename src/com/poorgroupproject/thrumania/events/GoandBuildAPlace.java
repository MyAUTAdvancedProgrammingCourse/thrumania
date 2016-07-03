package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.place.Place;
import com.poorgroupproject.thrumania.pathfinder.Pair;

/**
 * Created by Saman A.Mirhoseini on 03/07/2016.
 */
public class GoandBuildAPlace extends Event{
    public Pair target;
    public Place place;

    public GoandBuildAPlace(GameObject sender,Pair p,Place place) {
        super(sender);
        this.target = p;
        this.place = place;
    }
}
