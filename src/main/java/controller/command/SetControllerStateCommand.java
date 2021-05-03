package controller.command;

import controller.GameController;
import controller.GameControllerState;

public class SetControllerStateCommand implements Command {
    private GameController controller;
    private GameControllerState controllerState;

    public SetControllerStateCommand(GameController controller, GameControllerState controllerState) {
        this.controller = controller;
        this.controllerState = controllerState;
    }

    @Override
    public void execute() {
        this.controller.setGameControllerState(this.controllerState);
    }
}
