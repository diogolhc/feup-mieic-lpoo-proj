import controller.FarmController;
import controller.GameController;
import gui.GUI;
import gui.LanternaGUI;
import model.Farm;
import model.GameModel;
import viewer.FarmViewer;
import viewer.GameViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;
    private final GameModel model;
    private final GameController controller;
    private final GameViewer viewer;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        this.gui = new LanternaGUI(40, 20);

        this.model = new GameModel();
        this.viewer = new GameViewer(this.gui);
        this.controller = new GameController(this.viewer, this.model);
    }

    public GameController getController() {
        return this.controller;
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.getController().run();
    }
}
