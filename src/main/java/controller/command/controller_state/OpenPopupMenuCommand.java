package controller.command.controller_state;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.menu.MenuController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;

import static java.awt.SystemColor.menu;

public class OpenPopupMenuCommand implements Command {
    private final GameController controller;
    private final PopupMenuControllerBuilder menuBuilder;

    public OpenPopupMenuCommand(GameController controller, PopupMenuControllerBuilder menuBuilder) {
        this.controller = controller;
        this.menuBuilder = menuBuilder;
    }

    @Override
    public void execute() {
        MenuController popupController = menuBuilder.buildMenuCentered(
                controller.getWindowWidth(), controller.getWindowHeight());

        this.controller.setGameControllerState(popupController);
    }

    public PopupMenuControllerBuilder getMenuBuilder() {
        return this.menuBuilder;
    }
}
