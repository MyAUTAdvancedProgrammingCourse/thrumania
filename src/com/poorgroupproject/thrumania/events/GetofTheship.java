package com.poorgroupproject.thrumania.events;

import com.poorgroupproject.thrumania.item.GameObject;
import com.poorgroupproject.thrumania.item.human.Human;

/**
 * Created by Saman A.Mirhoseini on 04/07/2016.
 */
public class GetofTheship extends Event{
    Human human;
    public GetofTheship(GameObject sender,Human h) {
        super(sender);
        this.human = h;
    }
}
