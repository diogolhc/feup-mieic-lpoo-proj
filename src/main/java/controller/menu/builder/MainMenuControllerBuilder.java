package controller.menu.builder;

import controller.GameController;
import controller.command.*;
import controller.menu.ButtonController;
import controller.menu.MainMenuController;
import controller.menu.MenuController;
import model.Position;
import model.farm.Farm;
import model.farm.builder.NewGameFarmBuilder;
import model.menu.Button;
import model.menu.Menu;

import java.util.List;

public class MainMenuControllerBuilder extends MenuControllerBuilder {
    private final GameController gameController;
    private final int width;
    private final int height;

    public MainMenuControllerBuilder(GameController gameController) {
        this.gameController = gameController;
        this.width = this.gameController.getWindowWidth();
        this.height = this.gameController.getWindowHeight();
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button newGameButton = new Button(new Position(10, 6), "NEW GAME");
        Command newGameCommand = new NewGameCommand(gameController);

        buttons.add(new ButtonController(newGameButton, newGameCommand));

        Button loadGameButton = new Button(new Position(10, 11), "LOAD GAME");
        Command loadGameCommand = new LoadGameCommand(gameController, "save.data");

        buttons.add(new ButtonController(loadGameButton, loadGameCommand));

        Button exitGameButton = new Button(new Position(10, 16), "EXIT");
        Command exitGameCommand = new ExitGameCommand(gameController);

        buttons.add(new ButtonController(exitGameButton, exitGameCommand));

        return buttons;
    }

    @Override
    protected MainMenuController getMenuController(Menu menu) {
        return new MainMenuController(menu, gameController);
    }

    @Override
    protected int getHeight() {
        return this.height;
    }

    @Override
    protected int getWidth() {
        return this.width;
    }

    @Override
    protected String getTitle() {
        return "FARMVILLE";
    }
}
