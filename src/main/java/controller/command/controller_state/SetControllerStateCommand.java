package controller.command.controller_state;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;

public class SetControllerStateCommand implements Command {
    private final GameController controller;
    private final GameControllerState controllerState;

    public SetControllerStateCommand(GameController controller, GameControllerState controllerState) {
        this.controller = controller;
        this.controllerState = controllerState;
    }

    @Override
    public void execute() {
        this.controller.setGameControllerState(this.controllerState);
    }

    public GameControllerState getControllerState() {
        return this.controllerState;
    }
}
