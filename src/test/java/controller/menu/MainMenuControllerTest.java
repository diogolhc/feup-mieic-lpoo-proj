package controller.menu;

import controller.GameController;
import controller.command.Command;
import controller.farm.FarmWithFarmerController;
import controller.menu.element.ButtonController;
import gui.GUI;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

import java.util.ArrayList;
import java.util.List;

public class MainMenuControllerTest {
    private Menu menu;
    private GameController gameController;
    private MenuController controller;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        gameController = Mockito.mock(GameController.class);
        controller = new MainMenuController(menu, gameController);
    }

    @Test
    public void reactKeyboard() {
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_RIGHT);
        Mockito.verifyNoInteractions(gameController);
        controller.reactKeyboard(GUI.KEYBOARD_ACTION.BACK);
        Mockito.verify(gameController, Mockito.times(1))
                .setGameControllerState(Mockito.any(PopupMenuController.class));
    }
}
