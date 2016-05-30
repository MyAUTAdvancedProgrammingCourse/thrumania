package com.poorgroupproject.thrumania.item;

import java.awt.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public abstract class GameObject implements Runnable {
    protected Image currentImage;

    public Image getImage(){
        return currentImage;
    }
}
