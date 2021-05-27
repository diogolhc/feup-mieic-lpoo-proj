import controller.GameController;
import controller.menu.MenuController;
import controller.menu.builder.MainMenuControllerBuilder;
import gui.GUI;
import gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GameController controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        GUI gui = new LanternaGUI(40, 21);

        this.controller = new GameController(gui);
        MenuController mainMenu = new MainMenuControllerBuilder(this.controller).buildMenu();
        this.controller.setGameControllerState(mainMenu);
    }

    public GameController getController() {
        return this.controller;
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.getController().run();
    }
}
