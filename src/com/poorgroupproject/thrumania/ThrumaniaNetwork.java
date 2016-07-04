package com.poorgroupproject.thrumania;

import com.poorgroupproject.thrumania.panel.NetworkSettingPanel;

import java.awt.*;

/**
 * Created by Ahmad on 7/3/2016.
 */
public class ThrumaniaNetwork {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NetworkSettingPanel n = new NetworkSettingPanel();
                n.setVisible(true);
            }
        });
    }
}
