package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.SetControllerStateCommand;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

public abstract class PopupMenuBuilder extends MenuBuilder {
    protected GameController controller;

    public PopupMenuBuilder(GameController controller) {
        this.controller = controller;
    }

    protected Command getClosePopupMenuCommand() {
        return new SetControllerStateCommand(this.controller, this.controller.getGameControllerState());
    }

    @Override
    protected void addButtonsAndLabels(Menu menu) {
        Button closeMenuButton = new Button(new Position(this.getWidth() - 3, 0), "X");
        closeMenuButton.setCommand(this.getClosePopupMenuCommand());
        menu.addButton(closeMenuButton);
    }
}
