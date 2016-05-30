/**
 * Created by ahmad on 5/30/16.
 */

import java.util.*;

public class TimerDemo extends TimerTask {
    public static void main(String[] args) {
        // creating timer task, timer
        TimerTask tasknew = new TimerDemo();
        Timer timer = new Timer();

        // scheduling the task at interval
        timer.schedule(tasknew,100, 100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
    // this method performs the task
    public void run() {
        System.out.println("timer working");
    }
}
