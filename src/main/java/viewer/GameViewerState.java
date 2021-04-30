package viewer;

import gui.GUI;
import model.GameModel;

import java.io.IOException;

public interface GameViewerState {
    void draw(GameModel model, GUI gui) throws IOException;
}
