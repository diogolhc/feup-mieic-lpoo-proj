package controller;

import controller.farm.InteractionListener;
import model.Position;

import java.util.HashSet;
import java.util.Set;

public class MouseListener {
    Set<MouseClickObserver> listeners;

    public MouseListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyClick(int x, int y) {
        for (MouseClickObserver listener: listeners) {
            listener.notifyClick(new Position(x, y));
        }
    }

    public void reset() {
        this.listeners.clear();
    }

    public void addListener(MouseClickObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(MouseClickObserver listener) {
        this.listeners.remove(listener);
    }
}
