package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import model.menu.Menu;

public class PauseMenuController extends PopupMenuController {
    public PauseMenuController(Menu menu, GameController controller, GameControllerState backState) {
        super(menu, controller, backState);
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {}
}
