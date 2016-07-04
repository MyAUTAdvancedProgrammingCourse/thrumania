package com.poorgroupproject.thrumania.panel;

import com.poorgroupproject.thrumania.land.Land;
import com.poorgroupproject.thrumania.network.Client;
import com.poorgroupproject.thrumania.network.Server;
import com.poorgroupproject.thrumania.util.GameEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;

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


    private boolean isUserHost;
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
        addEventListener();
    }

    private void setUpHostOrClientComponent(){
        hostOrClientPanel = new JPanel(null);
        hostOrClientPanel.setSize(panelsSize);
        hostOrClientPanel.setLocation(panelsLocation);
        hostOrClientPanel.setBackground(panelsColor);
        add(hostOrClientPanel);

        hostOrClientButtonGroup = new ButtonGroup();

        userIsHost = new JRadioButton("Start a new Host and let other clients join.");
        userIsHost.setLocation(100,30);
        userIsHost.setSize(new Dimension(300,30));

        userIsNotHost = new JRadioButton("Join to a remote host.",true);
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
        JLabel jLabel1 = new JLabel();
        jLabel1.setText("Waiting to connect to the server....");
        jLabel1.setSize(330,30);
        jLabel1.setLocation(100,100);
        clientToConnectPanel.add(jLabel1);
        byte[]serverAddress = new byte[4];
        for (int i = 0; i < 4; i++)
            serverAddress[i] = ((byte) Integer.parseInt(ipClientTextField[i].getText()));
        int port = Integer.parseInt(portTextField.getText());
        Client.getInstance().setUpConnection(serverAddress,port);
        jLabel1.setText("Sending client name ...");
        Client.getInstance().sendClientName(clientNameTextField.getText());
        jLabel1.setText("Getting map from the server...");
        if (Client.getInstance().getMapFromServer()){
            jLabel1.setText("Map loaded from the server successfully...");
        }else {
            jLabel1.setText("Cannot load the map form the server...");
        }

        add(clientToConnectPanel);
    }

    private void addEventListener(){
        userIsHost.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    isUserHost = true;
                }
            }
        });

        userIsNotHost.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    isUserHost = false;
                }
            }
        });

        nextButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isUserHost){
                    Land.getInstance().loadMapImageFile();
                    try {
                        Land.getInstance().loadMap(new File("resource/map/map1.tmf"));
                    } catch (FileNotFoundException exp) {
                        exp.printStackTrace();
                    }

                    Land.getInstance().redrawMap();
                    Server.setUpServer(6066);
                    Server.getInstance().sendMapToClients();
                }else{
                    setUpClientComponent();
                    getContentPane().remove(hostOrClientPanel);
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
