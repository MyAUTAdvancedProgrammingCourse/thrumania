package com.poorgroupproject.thrumania.util;

import java.awt.*;
import java.awt.image.VolatileImage;

/**
 * @author ahmad
 * @version 1.0.0
 */
public abstract class GameEngine extends Canvas {
    private VolatileImage offscreenFrame;
    private static Dimension screenDimension;
    private Graphics offscreenGraphics;

    public GameEngine(){
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenDimension);
    }

    public abstract void render();

    public static Dimension getScreenDimension(){
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        return screenDimension;
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }

    public void drawOnFrame(Image img, Rectangle boundry){
        offscreenGraphics.drawImage(img, ((int) boundry.getX()), ((int) boundry.getY()), ((int) boundry.getWidth()), ((int) boundry.getHeight()),null);
    }

    public void drawRectOnFrame(Rectangle boundry){
        offscreenGraphics.setColor(Color.black);
        if (boundry.getWidth() < 0){
            boundry.setLocation(((int) (boundry.getX() + boundry.getWidth())), ((int) boundry.getY()));
        }
        if (boundry.getHeight() < 0){
            boundry.setLocation((int) (boundry.getX()), ((int) (boundry.getY()  + boundry.getHeight())));
        }
        offscreenGraphics.drawRect(((int) boundry.getX()), ((int) boundry.getY()), Math.abs((int) boundry.getWidth()), Math.abs((int) boundry.getHeight()));
    }
    @Override
    public void paint(Graphics g) {
        // create the hardware accelerated image.
        createBackBuffer();

        // Main rendering loop. Volatile images may lose their contents.
        // This loop will continually render to (and produce if neccessary) volatile images
        // until the rendering was completed successfully.
        do {

            // Validate the volatile image for the graphics configuration of this
            // component. If the volatile image doesn't apply for thi,.n blhk jlms graphics configuration
            // (in other words, the hardware acceleration doesn't apply for the new device)
            // then we need to re-create it.
            GraphicsConfiguration gc = this.getGraphicsConfiguration();
            int valCode = offscreenFrame.validate(gc);

            // This means the device doesn't match up to this hardware accelerated image.
            if(valCode == VolatileImage.IMAGE_INCOMPATIBLE){
                createBackBuffer(); // recreate the hardware accelerated image.
            }

            offscreenGraphics = offscreenFrame.getGraphics();
            offscreenGraphics.setColor(Color.WHITE);
            offscreenGraphics.fillRect(0, 0, getWidth(), getHeight());

            render();

            // paint back buffer to main graphics
            g.drawImage(offscreenFrame, 0, 0, this);
            // Test if content is lost
        } while(offscreenFrame.contentsLost());
    }

    private void createBackBuffer(){
        GraphicsConfiguration configuration = getGraphicsConfiguration();
        offscreenFrame = configuration.createCompatibleVolatileImage(((int) screenDimension.getWidth()), ((int) screenDimension.getHeight()));
    }
}
