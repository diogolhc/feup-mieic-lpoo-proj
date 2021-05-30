package controller.menu;

import controller.GameController;
import controller.GameControllerState;
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
        this.menu = Mockito.mock(Menu.class);
        this.backState = Mockito.mock(GameControllerState.class);
        this.gameController = Mockito.mock(GameController.class);
        this.pauseMenuController = new PauseMenuController(this.menu, this.gameController, this.backState);
    }

    @Test
    public void reactTimePassed() {
        // Pause should disable passage of time of backState
        this.pauseMenuController.reactTimePassed(20);
        Mockito.verifyNoInteractions(this.backState);
    }
}
