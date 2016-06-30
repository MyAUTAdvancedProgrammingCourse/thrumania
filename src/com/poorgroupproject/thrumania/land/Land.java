package com.poorgroupproject.thrumania.land;


import com.poorgroupproject.thrumania.backgroundprocess.Season;
import com.poorgroupproject.thrumania.util.GameEngine;
import com.poorgroupproject.thrumania.util.ResourcePath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import com.poorgroupproject.thrumania.pathfinder.*;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Land {
    private static Land land = new Land();
    private BufferedImage mapImage;

    private int rows;
    private int cols;

    public static final int CELL_WIDTH = 120;
    public static final int CELL_HEIGHT = 120;

    private Image[] springLandImages;
    private Image[] summerLandImages;
    private Image[] fallLandImages;
    private Image[] winterLandImages;

    private Image springWaterImage;
    private Image summerWaterImage;
    private Image fallWaterImage;
    private Image winterWaterImage;



//    public enum Cell {
//        WATER, LAND, MOUNTAIN, TREE, FARM,  GOLD_MINE, IRON_MINE,
//    };

    public Cell[][] getCells() {
        return cells;
    }

    private Cell [][]cells;

    public Cell getCellContent(int row,int col){
        return cells[row][col];
    }

    public void loadMap(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        cells = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                //String cellContent = (new StringTokenizer(str)).nextToken();
                String cellContent = scanner.next();
                if ("WATER".equals(cellContent))
                    cells[i][j] = Cell.WATER;
                else if ("LAND".equals(cellContent))
                    cells[i][j] = Cell.LAND;
                else if ("MOUNTAIN".equals(cellContent))
                    cells[i][j] = Cell.MOUNTAIN;
                else if ("TREE".equals(cellContent))
                    cells[i][j] = Cell.TREE;
                else if ("FARM".equals(cellContent))
                    cells[i][j] = Cell.FARM;
                else if ("GOLD_MINE".equals(cellContent))
                    cells[i][j] = Cell.GOLD_MINE;
                else if ("IRON_MINE".equals(cellContent))
                    cells[i][j] = Cell.IRON_MINE;
                else{
                    System.err.println("UNKNOWN CELL CONTENT LOADED FROM THE MAP");
                    System.exit(-1);
                }
            }
        }
    }

    public void loadMapImageFile(){
        try {
            String path = ResourcePath.imagePath + "/tile/spring/";
            springLandImages[0] = ImageIO.read(new File(path + "no.png"));
            springLandImages[1] = ImageIO.read(new File(path + "od.png"));
            springLandImages[2] = ImageIO.read(new File(path + "or.png"));
            springLandImages[3] = ImageIO.read(new File(path + "sld.png"));
            springLandImages[4] = ImageIO.read(new File(path + "ou.png"));
            springLandImages[5] = ImageIO.read(new File(path + "ocntrud.png"));
            springLandImages[6] = ImageIO.read(new File(path + "slu.png"));
            springLandImages[7] = ImageIO.read(new File(path + "sl.png"));
            springLandImages[8] = ImageIO.read(new File(path + "ol.png"));
            springLandImages[9] = ImageIO.read(new File(path + "srd.png"));
            springLandImages[10] = ImageIO.read(new File(path + "ocntrlr.png"));
            springLandImages[11] = ImageIO.read(new File(path + "sd.png"));
            springLandImages[12] = ImageIO.read(new File(path + "sru.png"));
            springLandImages[13] = ImageIO.read(new File(path + "sr.png"));
            springLandImages[14] = ImageIO.read(new File(path + "su.png"));
            springLandImages[15] = ImageIO.read(new File(path + "cntr.png"));
            springWaterImage     = ImageIO.read(new File(path + "water.png"));

            path = ResourcePath.imagePath + "/tile/summer/";
            summerLandImages[0] = ImageIO.read(new File(path + "no.png"));
            summerLandImages[1] = ImageIO.read(new File(path + "od.png"));
            summerLandImages[2] = ImageIO.read(new File(path + "or.png"));
            summerLandImages[3] = ImageIO.read(new File(path + "sld.png"));
            summerLandImages[4] = ImageIO.read(new File(path + "ou.png"));
            summerLandImages[5] = ImageIO.read(new File(path + "ocntrud.png"));
            summerLandImages[6] = ImageIO.read(new File(path + "slu.png"));
            summerLandImages[7] = ImageIO.read(new File(path + "sl.png"));
            summerLandImages[8] = ImageIO.read(new File(path + "ol.png"));
            summerLandImages[9] = ImageIO.read(new File(path + "srd.png"));
            summerLandImages[10] = ImageIO.read(new File(path + "ocntrlr.png"));
            summerLandImages[11] = ImageIO.read(new File(path + "sd.png"));
            summerLandImages[12] = ImageIO.read(new File(path + "sru.png"));
            summerLandImages[13] = ImageIO.read(new File(path + "sr.png"));
            summerLandImages[14] = ImageIO.read(new File(path + "su.png"));
            summerLandImages[15] = ImageIO.read(new File(path + "cntr.png"));
            summerWaterImage     = ImageIO.read(new File(path + "water.png"));

            path = ResourcePath.imagePath + "/tile/fall/";
            fallLandImages[0] = ImageIO.read(new File(path + "no.png"));
            fallLandImages[1] = ImageIO.read(new File(path + "od.png"));
            fallLandImages[2] = ImageIO.read(new File(path + "or.png"));
            fallLandImages[3] = ImageIO.read(new File(path + "sld.png"));
            fallLandImages[4] = ImageIO.read(new File(path + "ou.png"));
            fallLandImages[5] = ImageIO.read(new File(path + "ocntrud.png"));
            fallLandImages[6] = ImageIO.read(new File(path + "slu.png"));
            fallLandImages[7] = ImageIO.read(new File(path + "sl.png"));
            fallLandImages[8] = ImageIO.read(new File(path + "ol.png"));
            fallLandImages[9] = ImageIO.read(new File(path + "srd.png"));
            fallLandImages[10] = ImageIO.read(new File(path + "ocntrlr.png"));
            fallLandImages[11] = ImageIO.read(new File(path + "sd.png"));
            fallLandImages[12] = ImageIO.read(new File(path + "sru.png"));
            fallLandImages[13] = ImageIO.read(new File(path + "sr.png"));
            fallLandImages[14] = ImageIO.read(new File(path + "su.png"));
            fallLandImages[15] = ImageIO.read(new File(path + "cntr.png"));
            fallWaterImage     = ImageIO.read(new File(path + "water.png"));

            path = ResourcePath.imagePath + "/tile/winter/";
            winterLandImages[0] = ImageIO.read(new File(path + "no.png"));
            winterLandImages[1] = ImageIO.read(new File(path + "od.png"));
            winterLandImages[2] = ImageIO.read(new File(path + "or.png"));
            winterLandImages[3] = ImageIO.read(new File(path + "sld.png"));
            winterLandImages[4] = ImageIO.read(new File(path + "ou.png"));
            winterLandImages[5] = ImageIO.read(new File(path + "ocntrud.png"));
            winterLandImages[6] = ImageIO.read(new File(path + "slu.png"));
            winterLandImages[7] = ImageIO.read(new File(path + "sl.png"));
            winterLandImages[8] = ImageIO.read(new File(path + "ol.png"));
            winterLandImages[9] = ImageIO.read(new File(path + "srd.png"));
            winterLandImages[10] = ImageIO.read(new File(path + "ocntrlr.png"));
            winterLandImages[11] = ImageIO.read(new File(path + "sd.png"));
            winterLandImages[12] = ImageIO.read(new File(path + "sru.png"));
            winterLandImages[13] = ImageIO.read(new File(path + "sr.png"));
            winterLandImages[14] = ImageIO.read(new File(path + "su.png"));
            winterLandImages[15] = ImageIO.read(new File(path + "cntr.png"));
            winterWaterImage     = ImageIO.read(new File(path + "water.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void redrawMap(){
        BufferedImage tempMapImage = new BufferedImage(cols * CELL_WIDTH,rows * CELL_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        Graphics mapGraphic = tempMapImage.getGraphics();
        Season.SeasonName s = Season.getInstance().getCurrentSeason();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                switch (cells[i][j]){
                    case LAND:
                        int counter = 0;
                        if (j > 0)
                            if (cells[i][j - 1] == Cell.LAND)
                                counter += 8;
                        if (i > 0)
                            if (cells[i - 1][j] == Cell.LAND)
                                counter += 1;
                        if (i < rows - 1)
                            if (cells[i + 1][j] == Cell.LAND)
                                counter += 4;
                        if (j < cols - 1)
                            if (cells[i][j + 1] == Cell.LAND)
                                counter += 2;

                        switch (s){
                            case Spring:
                                mapGraphic.drawImage(springLandImages[counter],j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Summer:
                                mapGraphic.drawImage(summerLandImages[counter],j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Fall:
                                mapGraphic.drawImage(fallLandImages[counter],j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Winter:
                                mapGraphic.drawImage(winterLandImages[counter],j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                        }
                        break;
                    case WATER:
                        switch (s){
                            case Spring:
                                if (springWaterImage == null)
                                    System.out.println("this is error");
                                mapGraphic.drawImage(springWaterImage,j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Summer:
                                mapGraphic.drawImage(summerWaterImage,j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Fall:
                                mapGraphic.drawImage(fallWaterImage,j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                            case Winter:
                                mapGraphic.drawImage(winterWaterImage,j * CELL_WIDTH, i * CELL_HEIGHT,CELL_WIDTH,CELL_HEIGHT,null);
                                break;
                        }
                        break;
                    case MOUNTAIN:
                        // Todo add the needed code
                        break;

                    default:
                        System.out.println("some error");
                }
            }
        }
        mapGraphic.dispose();

        mapImage = tempMapImage;
    }

    public void seasonChanged(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                redrawMap();
            }
        }).start();
    }
    public BufferedImage getMapInBoundry(Rectangle boundry){
        return mapImage.getSubimage(((int) boundry.getX()), ((int) boundry.getY()), ((int) boundry.getWidth()), ((int) boundry.getHeight()));
    }
    private void loadMapImage() {
        mapImage = new BufferedImage(((int) GameEngine.getScreenDimension().getWidth()), ((int) GameEngine.getScreenDimension().getHeight()),BufferedImage.TYPE_INT_ARGB);
        new Thread(new Runnable() {
            @Override
            public void run() {
                redrawMap();
            }
        }).start();
    }

    private Land(){
        springLandImages = new Image[16];
        summerLandImages = new Image[16];
        fallLandImages = new Image[16];
        winterLandImages = new Image[16];
        mapImage = new BufferedImage(((int) GameEngine.getScreenDimension().getWidth()), ((int) GameEngine.getScreenDimension().getHeight()),BufferedImage.TYPE_INT_ARGB);
    }

    public static Land getInstance(){
        return land;
    }
}
