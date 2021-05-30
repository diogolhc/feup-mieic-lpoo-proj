package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PopupMenuControllerWithTimePassedReactionTest {
    private Menu menu;
    private GameControllerState backState;
    private GameController gameController;
    private PopupMenuController popupMenuController;
    private Command command;

    @BeforeEach
    public void setUp() {
        menu = Mockito.mock(Menu.class);
        backState = Mockito.mock(GameControllerState.class);
        gameController = Mockito.mock(GameController.class);
        command = Mockito.mock(Command.class);
        popupMenuController = new PopupMenuControllerWithTimePassedReaction(menu, gameController, backState, command);
    }

    @Test
    public void reactTimePassed() {
        popupMenuController.reactTimePassed(20);
        Mockito.verify(command, Mockito.times(1)).execute();

        // Popup should not disable passage of time of backState
        Mockito.verify(backState, Mockito.times(1)).reactTimePassed(20);
    }
}
