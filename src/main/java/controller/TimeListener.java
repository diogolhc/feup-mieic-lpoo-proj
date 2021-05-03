package controller;

import java.util.HashSet;
import java.util.Set;


public class TimeListener {
    Set<TimeObserver> listeners;

    public TimeListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyTimeChange(int minutes) {
        for (TimeObserver listener : listeners) {
            listener.notifyTimeChange(minutes);
        }
    }

    public void addListener(TimeObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(TimeObserver listener) {
        this.listeners.remove(listener);
    }

    public void reset() {
        this.listeners.clear();
    }


}
