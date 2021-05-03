package controller.command;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.PopupMenuController;
import model.menu.Menu;

public class OpenPopupMenuCommand implements Command {
    private GameController controller;
    private Menu menu;

    public OpenPopupMenuCommand(GameController controller, Menu menu) {
        this.controller = controller;
        this.menu = menu;
    }

    @Override
    public void execute() {
        GameControllerState newControllerState = new PopupMenuController(
                this.menu,
                this.controller,
                this.controller.getGameControllerState()
        );
        this.controller.setGameControllerState(newControllerState);
    }
}
