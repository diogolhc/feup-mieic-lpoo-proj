package viewer.menu;

import gui.GUI;
import model.GameModel;
import viewer.GameViewerState;

import java.io.IOException;

public class MenuViewer implements GameViewerState {
    @Override
    public void draw(GameModel model, GUI gui) throws IOException {
        gui.drawChar(0, 0, 'T');
        gui.drawChar(1, 0, 'O');
        gui.drawChar(2, 0, 'D');
        gui.drawChar(3, 0, 'O');
    }
}
