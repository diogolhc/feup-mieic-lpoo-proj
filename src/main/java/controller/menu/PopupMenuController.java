package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MenuViewer;
import viewer.menu.PopupMenuViewer;

public class PopupMenuController extends MenuController {
    private GameControllerState backState;

    public PopupMenuController(Menu menu, GameController controller, GameControllerState backState) {
        super(menu, controller);
        this.backState = backState;
    }

    @Override
    public void reactTimePassed() {
        // Popups should not stop the time of the things in the background
        backState.reactTimePassed();
    }

    @Override
    public GameViewer getViewer() {
        return new PopupMenuViewer(this.getMenu(), this.backState.getViewer());
    }
}
