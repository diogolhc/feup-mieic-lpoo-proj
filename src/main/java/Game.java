import controller.FarmController;
import gui.GUI;
import gui.LanternaGUI;
import model.Farm;
import viewer.FarmViewer;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final GUI gui;

    private final Farm farm;
    private final FarmController controller;
    private final FarmViewer viewer;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        gui = new LanternaGUI(40, 20);

        farm = new Farm();
        controller = new FarmController(farm);
        viewer = new FarmViewer(gui);
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        Game game = new Game();
        game.start();
    }

    private void start() throws IOException {
        while (true) {
            viewer.draw(farm);

            GUI.ACTION action = gui.getNextAction();
            if (action == GUI.ACTION.QUIT) break;

            controller.getFarmerController().doAction(action);
        }

        gui.close();
    }
}
