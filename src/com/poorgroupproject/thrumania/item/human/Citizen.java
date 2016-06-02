package com.poorgroupproject.thrumania.item.human;

/**
 * Created by ahmad on 5/30/16.
 */
public class Citizen extends Human {
    private int x;
    private int y;
    public Citizen(){
        super();
        x = 0;
        y = 0;
    }

    @Override
    public void run() {
        while (true){
            x += 10;
            y += 10;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
