import controller.GameController;
import controller.menu.builder.MainMenuControllerBuilder;
import gui.GUI;
import gui.LanternaGUI;
import model.Position;
import model.farm.Farm;
import model.farm.builder.NewGameFarmBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GameController controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        int width = 40;
        int height = 21;

        GUI gui = new LanternaGUI(width, height);
        Farm farm = new NewGameFarmBuilder().buildFarm();

        this.controller = new GameController(gui);
        this.controller.setGameControllerState(new MainMenuControllerBuilder(controller, farm, width, height).buildMenu());
    }

    public GameController getController() {
        return this.controller;
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.getController().run();
    }
}
