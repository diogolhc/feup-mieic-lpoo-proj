package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.PopupMenuViewer;

public class PopupMenuController extends MenuController {
    private GameControllerState backState;

    public PopupMenuController(Menu menu, GameController gameController, GameControllerState backState) {
        super(menu, gameController);
        this.backState = backState;
    }

    public void closePopup() {
        this.gameController.setGameControllerState(backState);
    }

    @Override
    public void reactKeyboard(GUI.ACTION action) {
        if (action == GUI.ACTION.BACK) {
            this.closePopup();
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
