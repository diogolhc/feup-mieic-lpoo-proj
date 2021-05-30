package controller.command.controller_state;

import controller.GameController;
import controller.command.Command;
import controller.menu.MenuController;
import controller.menu.builder.PopupMenuControllerBuilder;

public class OpenPopupMenuCommand implements Command {
    private final GameController controller;
    private final PopupMenuControllerBuilder menuBuilder;

    public OpenPopupMenuCommand(GameController controller, PopupMenuControllerBuilder menuBuilder) {
        this.controller = controller;
        this.menuBuilder = menuBuilder;
    }

    @Override
    public void execute() {
        MenuController popupController = this.menuBuilder.buildMenuCentered(
                this.controller.getWindowWidth(), this.controller.getWindowHeight());

        this.controller.setGameControllerState(popupController);
    }

    public PopupMenuControllerBuilder getMenuBuilder() {
        return this.menuBuilder;
    }
}
