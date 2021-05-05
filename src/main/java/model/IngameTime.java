package model;

public class IngameTime {
    private int minutes;

    public IngameTime(int minutes) {
        this.minutes = minutes;
    }

    public IngameTime(int day, int hour, int minute) {
        this(minute + (hour + day*24)*60);
    }

    public IngameTime() {
        this(0);
    }

    public void set(IngameTime time) {
        this.minutes = time.getMinute();
    }

    public int getMinute() {
        return minutes;
    }

    public int getDay() {
        return minutes / 60 / 24;
    }

    public int getHourOfDay() {
        return (minutes / 60) % 24;
    }

    public int getMinuteOfHour() {
        return minutes % 60;
    }

    @Override
    public String toString() {
        return String.format("Day %03d  %02d:%02d", this.getDay(), this.getHourOfDay(), this.getMinuteOfHour());
    }

    // TODO I don't know if this is the best way to go
    public String toCountdownString() {
        return new StringBuilder()
                .append(this.getHourOfDay())
                .append(':')
                .append(this.getMinuteOfHour())
                .toString();
    }
}
