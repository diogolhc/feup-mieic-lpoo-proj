import controller.GameController;
import controller.farm.FarmController;
import gui.GUI;
import gui.LanternaGUI;
import model.farm.Farm;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GameController controller;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        GUI gui = new LanternaGUI(40, 21);
        Farm farm = new Farm(40, 20);

        this.controller = new GameController(gui, farm);
    }

    public GameController getController() {
        return this.controller;
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.getController().run();
    }
}
