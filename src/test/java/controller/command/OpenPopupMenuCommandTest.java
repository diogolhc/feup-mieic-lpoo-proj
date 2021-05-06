package controller.command;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.PopupMenuController;
import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OpenPopupMenuCommandTest {
    private GameController gameController;
    private GameControllerState backState;
    private Menu menu;
    private OpenPopupMenuCommand command;

    @BeforeEach
    public void setUp() {
        menu = Mockito.mock(Menu.class);
        backState = Mockito.mock(GameControllerState.class);
        gameController = new GameController(Mockito.mock(GUI.class), backState);

        command = new OpenPopupMenuCommand(gameController, menu);
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
