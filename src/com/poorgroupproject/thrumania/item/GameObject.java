package com.poorgroupproject.thrumania.item;

import java.awt.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public abstract class GameObject implements Runnable {
    private int life;

    private Image currentImage;

    public Image getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(Image currentImage) {
        this.currentImage = currentImage;
    }
}
