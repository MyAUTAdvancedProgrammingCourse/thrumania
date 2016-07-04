package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.network.Server;
import com.poorgroupproject.thrumania.util.GameEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class NetworkSettingPanel extends JFrame {
    private Point panelsLocation;
    private Dimension panelsSize;
    private Color panelsColor;

    private JPanel hostOrClientPanel;
    private ButtonGroup hostOrClientButtonGroup;
    private JRadioButton userIsHost;
    private JRadioButton userIsNotHost;
    private JTextField portTextField;
    private JTextField []ipHostTextField;
    private JTextField []ipClientTextField;
    private JButton nextButton;
    private JTextField clientNameTextField;

    private JPanel clientToConnectPanel;


    private int screenWidth;
    private int screenHeight;

    public NetworkSettingPanel(){
        screenWidth = ((int) GameEngine.getScreenDimension().getWidth());
        screenHeight = ((int) GameEngine.getScreenDimension().getHeight());
        setForm();
        panelsSize = new Dimension(640,400);
        panelsLocation = new Point((((int) (screenWidth - panelsSize.getWidth()))) / 2, (((int) (screenHeight - panelsSize.getHeight()))) / 2);
        panelsColor = Color.cyan;
        setUpHostOrClientComponent();
    }

    private void setUpHostOrClientComponent(){
        hostOrClientPanel = new JPanel(null);
        hostOrClientPanel.setSize(panelsSize);
        hostOrClientPanel.setLocation(panelsLocation);
        hostOrClientPanel.setBackground(panelsColor);
        add(hostOrClientPanel);

        hostOrClientButtonGroup = new ButtonGroup();

        userIsHost = new JRadioButton();
        userIsHost.setText("Start a new Host and let other clients join.");
        userIsHost.setLocation(100,30);
        userIsHost.setSize(new Dimension(300,30));

        userIsNotHost = new JRadioButton();
        userIsNotHost.setText("Join to a remote host.");
        userIsNotHost.setLocation(100,200);
        userIsNotHost.setSize(new Dimension(300,30));

        hostOrClientButtonGroup.add(userIsHost);
        hostOrClientButtonGroup.add(userIsNotHost);

        hostOrClientPanel.add(userIsHost);
        hostOrClientPanel.add(userIsNotHost);


        JLabel portLable = new JLabel("Host port : ");
        portLable.setSize(100,30);
        portLable.setLocation(140,80);
        hostOrClientPanel.add(portLable);


        portTextField = new JTextField();
        portTextField.setSize(100,30);
        portTextField.setLocation(200,80);
        hostOrClientPanel.add(portTextField);

        String []ipLocal = Server.getLocalIPAddress();
        ipHostTextField = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            ipHostTextField[i] = new JTextField();
            ipHostTextField[i].setEditable(false);
            ipHostTextField[i].setSize(70,30);
            ipHostTextField[i].setLocation(100 + i * 80,130);
            ipHostTextField[i].setText(ipLocal[i]);
            hostOrClientPanel.add(ipHostTextField[i]);
        }

        JLabel playerNameLabel = new JLabel("Player name : ");
        playerNameLabel.setSize(120,30);
        playerNameLabel.setLocation(100,240);
        hostOrClientPanel.add(playerNameLabel);

        clientNameTextField = new JTextField();
        clientNameTextField.setSize(120,30);
        clientNameTextField.setLocation(230,240);
        hostOrClientPanel.add(clientNameTextField);

        ipClientTextField = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            ipClientTextField[i] = new JTextField();
            ipClientTextField[i].setSize(70,30);
            ipClientTextField[i].setLocation(100 + i * 80,280);
            hostOrClientPanel.add(ipClientTextField[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setSize(100,30);
        nextButton.setLocation(300,340);
        hostOrClientPanel.add(nextButton);
    }

    private void setUpClientComponent(){
        clientToConnectPanel = new JPanel(null);
        clientToConnectPanel.setLocation(panelsLocation);
        clientToConnectPanel.setSize(panelsSize);
        clientToConnectPanel.setBackground(panelsColor);

        JLabel jLabel1 = new JLabel("Waiting for the host to send the map.");
    }

    private void addEventListener(){
        userIsHost.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        userIsNotHost.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });
    }

    private void setForm(){
        setSize(GameEngine.getScreenDimension());
        setUndecorated(true);
        setLayout(null);
        setBackground(Color.black);
    }
}
