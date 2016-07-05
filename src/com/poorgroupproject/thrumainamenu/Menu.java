package com.poorgroupproject.thrumainamenu;

import com.poorgroupproject.thrumania.Thrumania;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author amin
 * @version 1.0.0
 */
public class Menu extends JFrame {


    private ImageIcon singleplayer;
    private ImageIcon multiplayer;
    private ImageIcon exit;
    private ImageIcon option;
    private ImageIcon mapEditor;

    private Rectangle singleplayerRect;
    private Rectangle multiplayerRect;
    private Rectangle optionRect;
    private Rectangle exitRect;
    private Rectangle mapEditorRect;

    private boolean isOnSingleplayer = false;
    private boolean isOnMultiplayer = false;
    private boolean isOnOption = false;
    private boolean isOnExit = false;
    private boolean isOnButton = false;
    private boolean isOnMapEditor = false;
    private boolean into1 = false;
    private boolean into2 = false;
    private boolean into3 = false;
    private boolean into4 = false;
    private boolean into5 = false;
    private boolean into6 = false;
    private boolean isFirstBeep = true;
    private boolean isTheFirstPaint= true;
    private boolean once = false;

    public Menu() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocation(0,0);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new Background());


        singleplayer = new ImageIcon("resources/button/singleplayer.png");
        multiplayer = new ImageIcon("resources/button/multiplayer.png");
        exit = new ImageIcon("resources/button/exit.png");
        option = new ImageIcon("resources/button/option.png");
        mapEditor = new ImageIcon("resources/button/mapEditor.png");
        singleplayerRect = new Rectangle(300,250,322,53);
        multiplayerRect = new Rectangle(320,320,324,50);
        optionRect = new Rectangle(470,390,192,50);
        exitRect=new Rectangle(545,465,123,54);
        mapEditorRect = new Rectangle(25,970,91,91);

        repaint();
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                Rectangle mouseRect = new Rectangle(mouseEvent.getX(),mouseEvent.getY(), 1, 1);

                if (mouseRect.intersects(singleplayerRect)) {
                    isOnSingleplayer = true;once = false;
                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent mouseEvent) {
                            if(!once){
                                new Thrumania();
                                once = true;
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseExited(MouseEvent mouseEvent) {

                        }
                    });
                } else{
                    isOnSingleplayer = false;
                }

                if (mouseRect.intersects(multiplayerRect)) {
                    isOnMultiplayer = true;
                    once = false;
                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent mouseEvent) {
                            if(!once){
                                new Thrumania();
                                once = true;
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseExited(MouseEvent mouseEvent) {

                        }
                    });
                } else{
                    isOnMultiplayer= false;
                }

                if (mouseRect.intersects(optionRect)) {
                    isOnOption= true;
                } else{
                    isOnOption= false;
                }

                if (mouseRect.intersects(exitRect)) {

                    isOnExit= true;
                } else{
                    isOnExit= false;
                }

                if (mouseRect.intersects(mapEditorRect)) {
                    isOnMapEditor= true;
                } else{
                    isOnMapEditor= false;
                }

                if (mouseRect.intersects(mapEditorRect) && mouseRect.intersects(exitRect) && mouseRect.intersects(optionRect) && mouseRect.intersects(singleplayerRect) && mouseRect.intersects(multiplayerRect)) {

                    isOnButton = true;
                } else{
                    isOnButton = false;
                }

                if (mouseRect.intersects(mapEditorRect) || mouseRect.intersects(exitRect) || mouseRect.intersects(optionRect) || mouseRect.intersects(singleplayerRect) || mouseRect.intersects(multiplayerRect)) {
                    if(isFirstBeep){
                        MP3 mp3 = new MP3 ("resources/sounds/beep.mp3");
                        mp3.play ();
                        isFirstBeep = false;
                    }
                } else{
                    isFirstBeep = true;
                }

                if (isOnSingleplayer){
//                    mapEditor = new ImageIcon("resources\\button\\mapeditor_.png");
                    singleplayer = new ImageIcon("resources/button/singleplayer.png");
                    multiplayer = new ImageIcon("resources/button/multiplayer_blur.png");
                    exit= new ImageIcon("resources/button\\exit_blur.png");
                    option = new ImageIcon("resources/button/option_blur.png");
                    if(!into1){
                        repaint();
                        System.out.println("sadasdasd");
                        into1 = true;
                        into2 = false;
                        into3 = false;
                        into4 = false;
                        into5 = false;
                        into6 = false;
                    }
                }else if (isOnMultiplayer){
//                    mapEditor = new ImageIcon("resources\\button\\mapeditor.png");
                    singleplayer = new ImageIcon("resources/button/singleplayer_blur.png");
                    multiplayer = new ImageIcon("resources/button/multiplayer.png");
                    exit= new ImageIcon("resources/button/exit_blur.png");
                    option = new ImageIcon("resources/button/option_blur.png");
                    if(!into2){
                        repaint();
                        into2 = true;
                        into1 = false;
                        into3 = false;
                        into4 = false;
                        into5 = false;
                        into6 = false;
                    }
                } else if (isOnOption){
//                    mapEditor = new ImageIcon("resources\\button\\mapeditor.png");
                    singleplayer = new ImageIcon("resources/button/singleplayer_blur.png");
                    multiplayer = new ImageIcon("resources/button/multiplayer_blur.png");
                    exit= new ImageIcon("resources/button/exit_blur.png");
                    option = new ImageIcon("resources/button/option.png");
                    if(!into3){
                        repaint();
                        into3 = true;
                        into1 = false;
                        into2 = false;
                        into4 = false;
                        into5 = false;
                        into6 = false;
                    }

                }else if (isOnExit){
//                    mapEditor = new ImageIcon("resources\\button\\mapeditor.png");
                    singleplayer = new ImageIcon("resources/button/singleplayer_blur.png");
                    multiplayer = new ImageIcon("resources/button/multiplayer_blur.png");
                    exit= new ImageIcon("resources/button/exit.png");
                    option = new ImageIcon("resources/button/option_blur.png");
                    if(!into4){
                        repaint();
                        into4 = true;
                        into1 = false;
                        into2 = false;
                        into3 = false;
                        into5 = false;
                        into6 = false;
                    }
                    addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent mouseEvent) {
                            System.exit(0);
                        }

                        @Override
                        public void mousePressed(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent mouseEvent) {

                        }

                        @Override
                        public void mouseExited(MouseEvent mouseEvent) {

                        }
                    });
                }
                else if(isOnMapEditor){
                    mapEditor = new ImageIcon("resources\\button\\mapeditor_clicked.png");
                    if (!into6){
                        repaint();
                        into6 = true;
                        into1 = false;
                        into2 = false;
                        into3 = false;
                        into4 = false;
                        into5 = false;
                    }
                } else if(!isOnButton){
                    mapEditor = new ImageIcon("resources\\button\\mapeditor.png");
                    singleplayer = new ImageIcon("resources\\button\\singleplayer.png");
                    multiplayer = new ImageIcon("resources\\button\\multiplayer.png");
                    exit= new ImageIcon("resources\\button\\exit.png");
                    option = new ImageIcon("resources\\button\\option.png");
                    if(!into5){
                        repaint();
                        into5 = true;
                        into1 = false;
                        into2 = false;
                        into3 = false;
                        into4 = false;
                        into6 = false;
                    }
                }

            }
        });

        setVisible(true);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(singleplayer.getImage(), 300, 250, null);
        g.drawImage(multiplayer.getImage(), 320, 320, null);
        g.drawImage(option.getImage(), 470, 390, null);
        g.drawImage(exit.getImage(), 545, 465, null);
//        g.drawRect(545, 465, 10 , 10);
        g.drawImage(mapEditor.getImage(), 25, 970, null);

    }

    public static void music(){
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        try{
            BGM = new AudioStream(new FileInputStream("sound.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        }catch(IOException error){
            System.out.print("file not found");
        }

        MGP.start(loop);
    }

    public static void main(String[] args) {
        new Menu();
        MP3 mp3 = new MP3 ("resources\\sounds\\sound.mp3");
        mp3.play ();
    }


}
