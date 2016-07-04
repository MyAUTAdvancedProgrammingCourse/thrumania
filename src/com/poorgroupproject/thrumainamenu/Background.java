package com.poorgroupproject.thrumainamenu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amin on 7/2/2016.
 */
public class Background extends JPanel {
    private ImageIcon background;

    public Background() {

        background = new ImageIcon("resource/image/background/background.jpg");
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(background.getImage(), 0,0,null);
    }
}
