package controller;

import model.ChronologicalTime;


public class ChronologicalTimeController {
    private static final long SECS_TO_NANO = 1000000000;
    private long previousTime;
    private long leftOverTime;
    private int realSecToGameMinutesRate;

    ChronologicalTimeController(int realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
        this.previousTime = System.nanoTime();
        this.leftOverTime = 0;
    }

    // TODO to use when farmer is sleeping for example
    public void accelerateTime(double rate) {
        this.realSecToGameMinutesRate = (int)(rate * realSecToGameMinutesRate);
    }

    public void advanceTime(ChronologicalTime chronologicalTime) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - this.previousTime + this.leftOverTime;

        this.previousTime = currentTime;
        this.leftOverTime = elapsedTime % SECS_TO_NANO;

        chronologicalTime.advance(realSecToGameMinutesRate * (int)(elapsedTime / SECS_TO_NANO));
    }

}
