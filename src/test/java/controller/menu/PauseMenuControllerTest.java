package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PauseMenuControllerTest {
    private Menu menu;
    private GameControllerState backState;
    private GameController gameController;
    private PopupMenuController pauseMenuController;


    @BeforeEach
    public void setUp() {
        menu = Mockito.mock(Menu.class);
        backState = Mockito.mock(GameControllerState.class);
        gameController = Mockito.mock(GameController.class);
        pauseMenuController = new PauseMenuController(menu, gameController, backState);
    }

    @Test
    public void reactTimePassed() {
        // Pause should disable passage of time of backState
        pauseMenuController.reactTimePassed(20);
        Mockito.verifyNoInteractions(backState);
    }
}
