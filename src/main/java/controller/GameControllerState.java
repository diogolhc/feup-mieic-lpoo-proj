package controller;

import gui.GUI;
import model.Position;
import viewer.GameViewer;

public interface GameControllerState {
    void reactChangeState();
    void reactKeyboard(GUI.KEYBOARD_ACTION action);
    void reactMouseMovement(Position position);
    void reactMouseClick(Position position);
    void reactTimePassed(long elapsedTimeSinceLastFrame);
    GameViewer getViewer();
}
