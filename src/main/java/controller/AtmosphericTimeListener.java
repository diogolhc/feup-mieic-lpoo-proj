package controller;

import model.AtmosphericTime;

import java.util.HashSet;
import java.util.Set;


public class AtmosphericTimeListener {
    Set<AtmosphericTimeObserver> listeners;

    public AtmosphericTimeListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyAtmosphericTimeChange(AtmosphericTime.TYPE type) {
        for (AtmosphericTimeObserver listener : listeners) {
            listener.notifyAtmosphericTimeChange(type);
        }
    }

    public void addListener(AtmosphericTimeObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(AtmosphericTimeObserver listener) {
        this.listeners.remove(listener);
    }

    public void reset() {
        this.listeners.clear();
    }

}
