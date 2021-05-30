package controller.menu;

import controller.GameController;
import controller.GameControllerState;
import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PopupMenuControllerTest {
    private Menu menu;
    private GameControllerState backState;
    private GameController gameController;
    private PopupMenuController popupMenuController;


    @BeforeEach
    public void setUp() {
        this.menu = Mockito.mock(Menu.class);
        this.backState = Mockito.mock(GameControllerState.class);
        this.gameController = Mockito.mock(GameController.class);
        this.popupMenuController = new PopupMenuController(this.menu, this.gameController, this.backState);
    }

    @Test
    public void reactKeyboard() {
        this.popupMenuController.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verifyNoInteractions(this.gameController);
        this.popupMenuController.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(this.backState);

        // Popup should disable keyboard reactions of backState
        Mockito.verify(this.backState, Mockito.never()).reactKeyboard(Mockito.any());
    }

    @Test
    public void reactTimePassed() {
        // Popup should not disable passage of time of backState
        this.popupMenuController.reactTimePassed(20);
        Mockito.verify(this.backState, Mockito.times(1)).reactTimePassed(20);
    }
}
