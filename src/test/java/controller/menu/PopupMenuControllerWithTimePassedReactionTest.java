package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
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
        this.menu = Mockito.mock(Menu.class);
        this.backState = Mockito.mock(GameControllerState.class);
        this.gameController = Mockito.mock(GameController.class);
        this.command = Mockito.mock(Command.class);
        this.popupMenuController = new PopupMenuControllerWithTimePassedReaction(this.menu, this.gameController, this.backState, this.command);
    }

    @Test
    public void reactTimePassed() {
        this.popupMenuController.reactTimePassed(20);
        Mockito.verify(this.command, Mockito.times(1)).execute();

        // Popup should not disable passage of time of backState
        Mockito.verify(this.backState, Mockito.times(1)).reactTimePassed(20);
    }
}
