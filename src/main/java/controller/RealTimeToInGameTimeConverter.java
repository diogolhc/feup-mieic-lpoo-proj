package controller;

import model.InGameTime;

public class RealTimeToInGameTimeConverter {
    private static final long SECS_TO_MILLI = 1000;
    private double realSecToGameMinutesRate;
    private long leftOverTimeMs;

    public RealTimeToInGameTimeConverter(double realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
        this.leftOverTimeMs = 0;
    }

    public void setRate(double realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
    }

    public double getRate() {
        return this.realSecToGameMinutesRate;
    }

    // TODO to use when farmer is sleeping for example
    //      the base rate will have to be stored so that it
    //      can be later reset (setBaseRate()?)
    public void accelerateTime(double rate) {
        this.realSecToGameMinutesRate *= rate;
    }

    public InGameTime convert(long elapsedTimeSinceLastFrameMilliSeconds) {
        long time = elapsedTimeSinceLastFrameMilliSeconds + this.leftOverTimeMs;
        this.leftOverTimeMs = time % SECS_TO_MILLI;

        return new InGameTime((int)((time / SECS_TO_MILLI) * realSecToGameMinutesRate));
    }

}
