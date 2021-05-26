package controller.menu.builder;

import controller.GameController;
import controller.command.Command;
import controller.command.ExitGameCommand;
import controller.command.NewGameCommand;
import controller.command.NoOperationCommand;
import controller.menu.ButtonController;
import controller.menu.MainMenuController;
import controller.menu.MenuController;
import controller.menu.PauseMenuController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

import java.util.List;

public class PauseMenuControllerBuilder extends PopupMenuControllerBuilder {

    public PauseMenuControllerBuilder(GameController controller) {
        super(controller);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button returnToMainMenuButton = new Button(new Position(1, 6), "RETURN TO MAIN MENU");
        Command returnToMainMenuCommand = new NoOperationCommand();

        buttons.add(new ButtonController(returnToMainMenuButton, returnToMainMenuCommand));

        return buttons;
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        return new PauseMenuController(menu, this.controller, this.controller.getGameControllerState());
    }

    @Override
    protected int getHeight() {
        return 10;
    }

    @Override
    protected int getWidth() {
        return 25;
    }

    @Override
    protected String getTitle() {
        return "PAUSED GAME";
    }
}
