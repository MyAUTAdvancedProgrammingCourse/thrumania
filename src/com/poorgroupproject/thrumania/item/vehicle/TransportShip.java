package com.poorgroupproject.thrumania.item.vehicle;

/**
 * @author yahay
 * @version 1.0.0
 */
public class TransportShip  extends Ship {
    public TransportShip(int x, int y) {
        super(x, y, 256, 256);
        (new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    setX(getX()+1);
                    setY(getY()+1);
                }

            }
        })).start();
    }


}
