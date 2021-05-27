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
        menu = Mockito.mock(Menu.class);
        backState = Mockito.mock(GameControllerState.class);
        gameController = Mockito.mock(GameController.class);
        popupMenuController = new PopupMenuController(menu, gameController, backState);
    }

    @Test
    public void closePopup() {
        popupMenuController.closePopup();
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(backState);
    }

    @Test
    public void reactKeyboard() {
        popupMenuController.reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(gameController, Mockito.never()).setGameControllerState(Mockito.any());
        popupMenuController.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(backState);

        // Popup should disable keyboard reactions of backState
        Mockito.verify(backState, Mockito.never()).reactKeyboard(Mockito.any());
    }

    @Test
    public void reactTimePassed() {
        // Popup should not disable passage of time of backState
        popupMenuController.reactTimePassed(20);
        Mockito.verify(backState, Mockito.times(1)).reactTimePassed(20);
    }
}
