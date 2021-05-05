package viewer;

import gui.GUI;

import java.io.IOException;

public abstract class GameViewer {
    public abstract void draw(GUI gui);

    public void drawScreen(GUI gui) throws IOException {
        gui.clear();
        this.draw(gui);
        gui.refresh();
    }
}
