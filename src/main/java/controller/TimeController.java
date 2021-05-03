package controller;

import model.IngameTime;


public class TimeController {
    private static final long SECS_TO_NANO = 1000000000;
    private long previousTime;
    private long leftOverTime;
    private double realSecToGameMinutesRate;

    public TimeController(double realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
        this.previousTime = System.nanoTime();
        this.leftOverTime = 0;
    }

    // TODO to use when farmer is sleeping for example
    public void accelerateTime(double rate) {
        this.realSecToGameMinutesRate *= rate;
    }

    public void advanceTime(IngameTime time) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - this.previousTime + this.leftOverTime;

        this.previousTime = currentTime;
        this.leftOverTime = elapsedTime % SECS_TO_NANO;

        time.advance((int)(realSecToGameMinutesRate * (elapsedTime / SECS_TO_NANO)));
    }


}
