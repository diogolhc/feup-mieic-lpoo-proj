package viewer;

import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import controller.MouseListener;
import gui.GUI;
import model.GameModel;
import viewer.farm.FarmViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameViewer {
    private GUI gui;
    GameViewerState gameViewerState;

    public GameViewer(GUI gui) {
        this.gui = gui;
        this.gameViewerState = new FarmViewer();
    }

    public void draw(GameModel model) throws IOException {
        gui.clear();

        this.gameViewerState.draw(model, gui);

        gui.refresh();
    }

    public void setGameViewerState(GameViewerState state) {
        this.gameViewerState = state;
    }

    public GUI.ACTION getNextAction() throws IOException {
        return gui.getNextAction();
    }

    public void closeGUI() throws IOException {
        gui.close();
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.gui.setMouseListener(mouseListener);
    }
}
