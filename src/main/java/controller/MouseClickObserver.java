package controller;

import model.Position;

public interface MouseClickObserver {
    void notifyClick(Position mousePosition);
}
