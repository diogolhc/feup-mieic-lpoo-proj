package controller;

import java.util.HashSet;
import java.util.Set;


public class ChronologicalTimeListener {
    Set<ChronologicalTimeObserver> listeners;

    public ChronologicalTimeListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyChronologicalTimeChange(int minutes) {
        for (ChronologicalTimeObserver listener : listeners) {
            listener.notifyChronologicalTimeChange(minutes);
        }
    }

    public void addListener(ChronologicalTimeObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ChronologicalTimeObserver listener) {
        this.listeners.remove(listener);
    }

    public void reset() {
        this.listeners.clear();
    }


}
