package model;

public class ChronologicalTime {
    private int minutes;

    public ChronologicalTime(int minutes) {
        this.minutes = minutes;
    }

    public void advance(int minutes) {
        this.minutes += minutes;
    }

    public int getMinutes() {
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

    // TODO I don't know if this is the best way to go
    public String toCountdownString() {
        return new StringBuilder()
                .append(minutes/60)
                .append(':')
                .append(minutes % 60)
                .toString();
    }
}
