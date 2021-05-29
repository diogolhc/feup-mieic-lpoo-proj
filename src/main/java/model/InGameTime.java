package model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class InGameTime implements Comparable<InGameTime>, Serializable {
    private final int minutes;

    public InGameTime(int minutes) {
        if (minutes < 0) minutes = 0;
        this.minutes = minutes;
    }

    public InGameTime(int day, int hour, int minute) {
        this(minute + (hour + day * 24) * 60);
    }

    public InGameTime() {
        this(0);
    }

    public InGameTime(InGameTime time) {
        this.minutes = time.minutes;
    }

    public static InGameTime getRandom(InGameTime min, InGameTime max) {
        if (min.compareTo(max) > 0) throw new IllegalArgumentException("min in range cannot be larger than max");

        int delta = max.minutes - min.minutes;
        return new InGameTime(min.minutes + (int) (Math.random() * delta));
    }

    public static InGameTime parseTimerString(String s) {
        String[] tokens = s.split(":");
        if (tokens.length == 2) {
            return new InGameTime(0, Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        } else if (tokens.length == 3) {
            return new InGameTime(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
        } else {
            throw new IllegalArgumentException("Can't parse string " + s + " as InGameTime.");
        }
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
        return new InGameTime(this.minutes + inGameTime.minutes);
    }

    public InGameTime subtract(InGameTime inGameTime) {
        return new InGameTime(this.minutes - inGameTime.minutes);
    }

    public String getDayTimeString() {
        return String.format("DAY %03d  %02d:%02d", this.getDay(), this.getHourOfDay(), this.getMinuteOfHour());
    }

    public String getTimerString() {
        if (this.getDay() == 0) {
            return String.format("%02d:%02d", this.getHourOfDay(), this.getMinuteOfHour());
        } else {
            return String.format("%d:%02d:%02d", this.getDay(), this.getHourOfDay(), this.getMinuteOfHour());
        }
    }

    @Override
    public String toString() {
        return this.getTimerString();
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
