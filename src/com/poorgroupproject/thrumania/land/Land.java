package com.poorgroupproject.thrumania.land;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Land {
    private static Land land = new Land();

    public enum Cell {
        WATER, LAND, MOUNTAIN, TREE, FARM, GOLD_MINE, IRON_MINE,
    };

    private Cell [][]cells;

    public Cell getCellContent(int row,int col){
        return cells[row][col];
    }

    public void loadMap(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
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

    private Land(){

    }

    public static Land getInstance(){
        return land;
    }
}
