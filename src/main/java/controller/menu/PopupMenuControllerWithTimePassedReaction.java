package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import model.menu.Menu;

public class PopupMenuControllerWithTimePassedReaction extends PopupMenuController {
    private final Command timePassedReaction;

    public PopupMenuControllerWithTimePassedReaction(Menu menu, GameController controller,
                                                     GameControllerState backState, Command timePassedReaction) {
        super(menu, controller, backState);
        this.timePassedReaction = timePassedReaction;
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {
        super.reactTimePassed(elapsedTimeSinceLastFrame);
        this.timePassedReaction.execute();
    }
}
