package controller;

import model.InGameTime;

public class RealTimeToInGameTimeConverter {
    private static final long SECS_TO_MILLI = 1000;
    private final long baseRate;
    private long rateMultiplier;
    private long leftoverTime;

    public RealTimeToInGameTimeConverter(long realSecToGameMinutesRate) {
        this.baseRate = realSecToGameMinutesRate;
        this.rateMultiplier = 1;
        this.leftoverTime = 0;
    }

    public long getRate() {
        return this.baseRate * this.rateMultiplier;
    }

    public void setRateMultiplier(long rateMultiplier) {
        this.rateMultiplier = rateMultiplier;
    }

    public InGameTime convert(long elapsedTimeSinceLastFrameMilliSeconds) {
        long convertedTime = elapsedTimeSinceLastFrameMilliSeconds * this.getRate() + this.leftoverTime;
        this.leftoverTime = convertedTime % SECS_TO_MILLI;

        return new InGameTime((int) (convertedTime / SECS_TO_MILLI));
    }
}
