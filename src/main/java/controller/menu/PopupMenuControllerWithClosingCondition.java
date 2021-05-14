package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import model.menu.Menu;

public class PopupMenuControllerWithClosingCondition extends PopupMenuController {
    private Command closingCondition;

    public PopupMenuControllerWithClosingCondition(Menu menu, GameController controller, GameControllerState backState,
                                                   Command closingCondition) {
        super(menu, controller, backState);
        this.closingCondition = closingCondition;
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        super.reactTimePassed(elapsedTimeSinceLastFrame);
        this.closingCondition.execute();
    }
}
