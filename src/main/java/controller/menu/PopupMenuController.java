package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.PopupMenuViewer;

public class PopupMenuController extends MenuController {
    private GameControllerState backState;
    private GameController controller;

    public PopupMenuController(Menu menu, GameController controller, GameControllerState backState) {
        super(menu);
        this.controller = controller;
        this.backState = backState;
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.BACK) {
            this.controller.setGameControllerState(backState);
        }
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        // Popups do not stop the time passing of the things in the background
        backState.reactTimePassed(elapsedTimeSinceLastFrame);
    }

    @Override
    public GameViewer getViewer() {
        return new PopupMenuViewer(this.getMenu(), this.backState.getViewer());
    }
}
