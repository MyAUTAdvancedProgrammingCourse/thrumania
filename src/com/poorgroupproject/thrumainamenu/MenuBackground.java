package com.poorgroupproject.thrumainamenu;

import javax.swing.*;
import java.awt.*;

/**
 * @author amin
 * @version 1.0.0
 */
public class MenuBackground extends JComponent {
    private Image image;
    public MenuBackground (Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
//            g.drawRect(30, 40, 100, Toolkit.getDefaultToolkit().getScreenSize().height / 40 * 6 );
    }
}
