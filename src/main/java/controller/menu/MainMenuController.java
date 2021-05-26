package controller.menu;

import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MainMenuViewer;

public class MainMenuController extends MenuController {
    public MainMenuController(Menu menu) {
        super(menu);
    }

    @Override
    public GameViewer getViewer() {
        return new MainMenuViewer(this.menu);
    }

}
