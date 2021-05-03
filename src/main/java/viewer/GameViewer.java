package viewer;

import gui.GUI;

import java.io.IOException;

public abstract class GameViewer {
    public abstract void drawScreen(GUI gui);

    public void draw(GUI gui) throws IOException {
        gui.clear();

        this.drawScreen(gui);

        gui.refresh();
    }
}
