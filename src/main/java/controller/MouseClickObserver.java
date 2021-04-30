package controller;

import model.Position;

public interface MouseClickObserver {
    void notifyPosition(Position mousePosition);
    void notifyClick(Position mousePosition);
}
