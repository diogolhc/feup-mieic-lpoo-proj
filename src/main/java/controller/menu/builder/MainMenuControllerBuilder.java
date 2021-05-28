package controller.menu.builder;

import controller.GameController;
import controller.command.*;
import controller.command.game.ExitGameCommand;
import controller.command.game.LoadGameCommand;
import controller.command.game.NewGameCommand;
import controller.menu.element.ButtonController;
import controller.menu.MainMenuController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

import java.util.List;

public class MainMenuControllerBuilder extends MenuControllerBuilder {
    private final GameController gameController;

    public MainMenuControllerBuilder(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        addNewGameButton(buttons);
        addLoadGameButton(buttons);
        addExitButton(buttons);

        return buttons;
    }

    private void addNewGameButton(List<ButtonController> buttons) {
        Button newGameButton = new Button(new Position(10, 6), "NEW GAME");
        Command newGameCommand = new NewGameCommand(gameController);
        buttons.add(new ButtonController(newGameButton, newGameCommand));
    }

    private void addLoadGameButton(List<ButtonController> buttons) {
        Button loadGameButton = new Button(new Position(10, 11), "LOAD GAME");
        Command loadGameCommand = new LoadGameCommand(gameController, "save.data");
        buttons.add(new ButtonController(loadGameButton, loadGameCommand));
    }

    private void addExitButton(List<ButtonController> buttons) {
        Button exitGameButton = new Button(new Position(10, 16), "EXIT");
        Command exitGameCommand = new ExitGameCommand(gameController);
        buttons.add(new ButtonController(exitGameButton, exitGameCommand));
    }

    @Override
    protected MainMenuController getMenuController(Menu menu) {
        return new MainMenuController(menu, gameController);
    }

    @Override
    protected int getHeight() {
        return this.gameController.getWindowHeight();
    }

    @Override
    protected int getWidth() {
        return this.gameController.getWindowWidth();
    }

    @Override
    protected String getTitle() {
        return "FARMVILLE";
    }
}
