package viewer.menu;

import gui.GUI;
import model.menu.Menu;
import viewer.GameViewer;

public class PopupMenuViewer extends MenuViewer {
    private GameViewer backViewer;

    public PopupMenuViewer(Menu menu, GameViewer backViewer) {
        super(menu);
        this.backViewer = backViewer;
    }

    @Override
    public void draw(GUI gui) {
        backViewer.draw(gui);
        super.draw(gui);
    }
}