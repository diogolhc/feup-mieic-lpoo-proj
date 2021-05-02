package model;

public class ChronologicalTime {
    private int minute;
    private int hour;
    private int day;

    public ChronologicalTime(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public ChronologicalTime() {
        this(0,0,0);
    }

    public void advance(int minutes) {
        this.minute += minutes;
        int left = this.minute / 60;
        this.minute %= 60;

        this.hour += left;
        left = this.hour / 24;
        this.hour %= 24;

        this.day += left;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return String.format("Day %d  %02d:%02d", this.day, this.hour, this.minute);
    }

}
