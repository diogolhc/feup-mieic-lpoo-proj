package controller.menu;

import controller.GameController;
import controller.command.game.ExitGameCommand;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.menu.builder.info.ConfirmationMenuControllerBuilder;
import gui.GUI;
import model.menu.Menu;
import viewer.GameViewer;
import viewer.menu.MainMenuViewer;

public class MainMenuController extends MenuController {
    public MainMenuController(Menu menu, GameController gameController) {
        super(menu, gameController);
    }

    @Override
    public void reactKeyboard(GUI.KEYBOARD_ACTION action) {
        if (action == GUI.KEYBOARD_ACTION.BACK) {
            new OpenPopupMenuCommand(this.gameController, new ConfirmationMenuControllerBuilder(this.gameController,
                    "EXIT GAME", "DO YOU WANT TO EXIT?")
                    .setYesCommand(new ExitGameCommand(this.gameController))
            ).execute();
        }
    }

    @Override
    public void reactTimePassed(long elapsedTimeSinceLastFrame) {}

    @Override
    public GameViewer getViewer() {
        return new MainMenuViewer(this.getMenu());
    }
}
