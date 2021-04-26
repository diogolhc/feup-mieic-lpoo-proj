package viewer;

import gui.GUI;
import model.Farm;
import model.Farmer;

import java.io.IOException;

public class GameViewer {
    private GUI gui;

    public GameViewer(GUI gui) {
        this.gui = gui;
    }

    public void drawFarm(Farm farm) throws IOException {
        FarmViewer farmViewer = new FarmViewer();
        farmViewer.draw(farm, gui);
    }

    public GUI.ACTION getNextAction() throws IOException {
        return gui.getNextAction();
    }

    public void closeGUI() throws IOException {
        gui.close();
    }
}
