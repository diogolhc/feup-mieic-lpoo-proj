package viewer.menu;

import gui.GUI;
import model.Position;
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

    @Override
    protected void drawTitle(GUI gui) {
        MenuTitleViewer menuTitleViewer = new MenuTitleViewer();
        menuTitleViewer.draw(this.menu, new Position(1, 1), gui);
    }
}
