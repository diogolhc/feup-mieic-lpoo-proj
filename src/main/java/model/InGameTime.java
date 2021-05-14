package model;

import java.util.Objects;

public class InGameTime implements Comparable<InGameTime> {
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

    public InGameTime add(InGameTime inGameTime) {
        this.minutes += inGameTime.getMinute();
        return this;
    }

    public InGameTime subtract(InGameTime inGameTime) {
        this.minutes -= inGameTime.getMinute();
        if (this.minutes < 0) this.minutes = 0;
        return this;
    }

    @Override
    public String toString() {
        return String.format("DAY %03d  %02d:%02d", this.getDay(), this.getHourOfDay(), this.getMinuteOfHour());
    }

    public String toCountdownString() {
        if (this.getDay() == 0) {
            return String.format("%02d:%02d", this.getHourOfDay(), this.getMinuteOfHour());
        } else {
            return String.format("%d:%02d:%02d", this.getDay(), this.getHourOfDay(), this.getMinuteOfHour());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InGameTime time = (InGameTime) o;
        return this.minutes == time.getMinute();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.minutes);
    }

    @Override
    public int compareTo(InGameTime inGameTime) {
        return Integer.compare(this.minutes, inGameTime.minutes);
    }
}
