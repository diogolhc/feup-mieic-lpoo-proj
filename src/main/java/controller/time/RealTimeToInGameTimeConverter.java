package controller.time;

import model.InGameTime;

public class RealTimeToInGameTimeConverter {
    private static final long SECS_TO_MILLI = 1000;
    private double realSecToGameMinutesRate;
    private long leftOverTimeMilliSecs;

    public RealTimeToInGameTimeConverter(double realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
        this.leftOverTimeMilliSecs = 0;
    }

    public void setRate(double realSecToGameMinutesRate) {
        this.realSecToGameMinutesRate = realSecToGameMinutesRate;
    }

    public double getRate() {
        return this.realSecToGameMinutesRate;
    }

    // TODO to use when farmer is sleeping for example
    public void accelerateTime(double rate) {
        this.realSecToGameMinutesRate *= rate;
    }

    public InGameTime convert(long elapsedTimeSinceLastFrameMilliSeconds) {
        long time = elapsedTimeSinceLastFrameMilliSeconds + this.leftOverTimeMilliSecs;
        this.leftOverTimeMilliSecs = time % SECS_TO_MILLI;

        return new InGameTime((int)((time / SECS_TO_MILLI) * realSecToGameMinutesRate));
    }

}
