package controller.command;

import controller.GameController;
import controller.GameControllerState;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.menu.PopupMenuController;
import controller.menu.builder.PopupMenuControllerBuilder;
import gui.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OpenPopupMenuCommandTest {
    private GameController gameController;
    private GameControllerState backState;
    private PopupMenuControllerBuilder menuControllerBuilder;
    private OpenPopupMenuCommand command;

    @BeforeEach
    public void setUp() {
        backState = Mockito.mock(GameControllerState.class);
        gameController = new GameController(Mockito.mock(GUI.class), backState);
        menuControllerBuilder = new PopupMenuControllerBuilder(gameController) {
            @Override
            protected int getHeight() {
                return 0;
            }

            @Override
            protected int getWidth() {
                return 0;
            }

            @Override
            protected String getTitle() {
                return null;
            }
        };

        command = new OpenPopupMenuCommand(gameController, menuControllerBuilder);
    }

    @Test
    public void execute() {
        command.execute();
        GameControllerState newState = gameController.getGameControllerState();

        Assertions.assertTrue(newState instanceof PopupMenuController);
        PopupMenuController newPopupController = (PopupMenuController) newState;
        newPopupController.closePopup();
        Assertions.assertSame(backState, gameController.getGameControllerState());
    }
}
