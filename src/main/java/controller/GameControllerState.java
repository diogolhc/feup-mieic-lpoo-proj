package controller;

import gui.GUI;
import model.GameModel;

public interface GameControllerState {
    void doAction(GameModel model, GUI.ACTION action);
}
