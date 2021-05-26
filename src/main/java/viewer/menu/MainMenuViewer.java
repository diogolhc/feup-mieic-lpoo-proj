package viewer.menu;

import gui.GUI;
import model.Position;
import model.menu.Menu;
import viewer.menu.MenuViewer;

public class MainMenuViewer extends MenuViewer {
    public MainMenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    protected void drawTitle(GUI gui) {
        MenuTitleViewer mainMenuTitleViewer = new MenuTitleViewer();
        mainMenuTitleViewer.draw(this.menu, new Position(15, 2), gui);
    }
}
