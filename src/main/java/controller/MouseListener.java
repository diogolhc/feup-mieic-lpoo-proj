package controller;

import model.Position;

import java.util.HashSet;
import java.util.Set;

public class MouseListener {
    Set<MouseObserver> listeners;

    public MouseListener() {
        this.listeners = new HashSet<>();
    }

    public void notifyMouseClick(int x, int y) {
        for (MouseObserver listener: listeners) {
            listener.notifyClick(new Position(x, y));
        }
    }

    public void notifyMouseMovement(int x, int y) {
        for (MouseObserver listener: listeners) {
            listener.notifyPosition(new Position(x, y));
        }
    }

    public void reset() {
        this.listeners.clear();
    }

    public void addListener(MouseObserver listener) {
        this.listeners.add(listener);
    }

    public void removeListener(MouseObserver listener) {
        this.listeners.remove(listener);
    }
}
