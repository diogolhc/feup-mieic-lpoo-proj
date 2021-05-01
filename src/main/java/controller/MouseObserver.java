package controller;

import model.Position;

public interface MouseObserver {
    void notifyPosition(Position mousePosition);
    void notifyClick(Position mousePosition);
}
