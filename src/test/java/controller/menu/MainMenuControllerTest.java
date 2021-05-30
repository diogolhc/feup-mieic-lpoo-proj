package controller.menu;

import controller.GameController;
import gui.GUI;
import model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MainMenuControllerTest {
    private Menu menu;
    private GameController gameController;
    private MenuController controller;

    @BeforeEach
    public void setUp() {
        this.menu = new Menu();
        this.gameController = Mockito.mock(GameController.class);
        this.controller = new MainMenuController(this.menu, this.gameController);
    }

    @Test
    public void reactKeyboard() {
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verifyNoInteractions(this.gameController);
        this.controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(this.gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(PopupMenuController.class));
    }
}
