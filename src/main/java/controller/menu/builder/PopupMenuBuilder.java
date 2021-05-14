package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.SetControllerStateCommand;
import controller.menu.ButtonController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public abstract class PopupMenuBuilder extends MenuBuilder {
    protected GameController controller;

    public PopupMenuBuilder(GameController controller) {
        this.controller = controller;
    }

    protected Command getClosePopupMenuCommand() {
        return new SetControllerStateCommand(this.controller, this.controller.getGameControllerState());
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        return new PopupMenuController(menu, this.controller, this.controller.getGameControllerState());
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button closeMenuButton = new Button(new Position(this.getWidth() - 3, 0), "X");
        buttons.add(new ButtonController(closeMenuButton, this.getClosePopupMenuCommand()));

        return buttons;
    }
}
