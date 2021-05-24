package controller.command;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;

import static java.awt.SystemColor.menu;

public class OpenPopupMenuCommand implements Command {
    private GameController controller;
    private PopupMenuControllerBuilder menuBuilder;

    public OpenPopupMenuCommand(GameController controller, PopupMenuControllerBuilder menuBuilder) {
        this.controller = controller;
        this.menuBuilder = menuBuilder;
    }

    @Override
    public void execute() {
        this.controller.setGameControllerState(menuBuilder.buildMenu(new Position(1, 1)));
    }
}
