package model;

public class ChronologicalTime {
    private int minutes;

    ChronologicalTime(int minutes) {
        this.minutes = minutes;
    }

    public void advance(int minutes) {
        this.minutes += minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getDay() {
        return minutes / 24;
    }

    public int getHourOfDay() {
        return (minutes / 60) % 24;
    }

    public int getMinuteOfHour() {
        return minutes % 60;
    }
}
