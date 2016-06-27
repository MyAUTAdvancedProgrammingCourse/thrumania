package com.poorgroupproject.thrumania.backgroundprocess;

import com.poorgroupproject.thrumania.land.Land;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ahmad
 * @version 1.0.0
 */
public class Season extends TimerTask {
    private static Season season = new Season();

    private Timer seasonChanger;
    private long seasonChangerInterval;
    private int seasonChangerIntervalCounter;
    private byte currentSeasonId;

    public enum SeasonName{
        Spring, Summer, Fall, Winter
    };

    private Season(){
        seasonChanger = new Timer(true);
        setSeasonChangerInterval();
        seasonChanger.schedule(this,0,seasonChangerInterval);
    }

    /**
     * seasonChangerInterval can be set in this method
     * A separate method is considered because it can be loaded from a setting file
     */
    private void setSeasonChangerInterval(){
        seasonChangerInterval = 60;
    }
    @Override
    public void run() {
        seasonChangerIntervalCounter ++;
        if (seasonChangerIntervalCounter % 30 == 0){
            currentSeasonId ++;
            currentSeasonId %= 4;
            Land.getInstance().seasonChanged();
            seasonChangerIntervalCounter = 0;
        }
    }


    public SeasonName getCurrentSeason(){
        switch (currentSeasonId){
            case 0:
                return SeasonName.Spring;
            case 1:
                return SeasonName.Summer;
            case 2:
                return SeasonName.Fall;
            case 3:
                return SeasonName.Winter;
            default:
                return null;
        }
    }
    public static Season getInstance(){
        return season;
    }
}
