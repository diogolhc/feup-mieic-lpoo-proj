package model;

public class InGameTime {
    private int minutes;

    public InGameTime(int minutes) {
        this.minutes = minutes;
    }

    public InGameTime(int day, int hour, int minute) {
        this(minute + (hour + day*24)*60);
    }

    public InGameTime() {
        this(0);
    }

    public void set(InGameTime time) {
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
        return String.format("%02d:%02d", this.getHourOfDay(), this.getMinuteOfHour());
    }
}
