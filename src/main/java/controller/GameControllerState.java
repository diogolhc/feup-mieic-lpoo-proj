package controller;

import gui.GUI;
import model.Position;
import viewer.GameViewer;

public interface GameControllerState {
    void reactKeyboard(GUI.ACTION action);
    void reactMouseMovement(Position position);
    void reactMouseClick(Position position);
    void reactTimePassed();
    GameViewer getViewer();
}
