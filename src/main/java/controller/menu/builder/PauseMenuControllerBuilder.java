package controller.menu.builder;

import controller.GameController;
import controller.command.*;
import controller.menu.*;
import controller.menu.builder.info.ConfirmationMenuControllerBuilder;
import model.Position;
import model.menu.Button;
import model.menu.Menu;

import java.util.List;

public class PauseMenuControllerBuilder extends PopupMenuControllerBuilder {

    public PauseMenuControllerBuilder(GameController controller) {
        super(controller);
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button returnToMainMenuButton = new Button(new Position(1, 4), "MAIN MENU");
        PopupMenuControllerBuilder confirmationPopup = new ConfirmationMenuControllerBuilder(
                this.controller,
                "RETURN TO MAIN MENU",
                "ARE YOU SURE YOU WANT TO EXIT?\nYOUR PROGRESS WILL NOT BE SAVED\nUNLESS YOU SAVE AT THE HOUSE FIRST")
                .setYesCommand(new SetControllerStateCommand(
                        this.controller,
                        new MainMenuControllerBuilder(this.controller).buildMenu()
                ));
        Command returnToMainMenuCommand = new OpenPopupMenuCommand(this.controller, confirmationPopup);

        buttons.add(new ButtonController(returnToMainMenuButton, returnToMainMenuCommand));

        return buttons;
    }

    @Override
    protected MenuController getMenuController(Menu menu) {
        return new PauseMenuController(menu, this.controller, this.controller.getGameControllerState());
    }

    @Override
    protected int getHeight() {
        return 8;
    }

    @Override
    protected int getWidth() {
        return 17;
    }

    @Override
    protected String getTitle() {
        return "PAUSED GAME";
    }
}
