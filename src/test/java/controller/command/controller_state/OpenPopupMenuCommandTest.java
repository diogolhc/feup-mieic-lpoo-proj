package controller.command.controller_state;

import controller.GameController;
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
    private PopupMenuControllerBuilder menuControllerBuilder;
    private OpenPopupMenuCommand command;

    @BeforeEach
    public void setUp() {
        GUI gui = Mockito.mock(GUI.class);
        Mockito.when(gui.getWindowWidth()).thenReturn(30);
        Mockito.when(gui.getWindowHeight()).thenReturn(20);

        this.gameController = new GameController(gui);
        this.menuControllerBuilder = Mockito.mock(PopupMenuControllerBuilder.class);

        Mockito.when(this.menuControllerBuilder.buildMenuCentered(30, 20))
                .thenReturn(Mockito.mock(PopupMenuController.class));

        this.command = new OpenPopupMenuCommand(this.gameController, this.menuControllerBuilder);
    }

    @Test
    public void execute() {
        Assertions.assertFalse(this.gameController.getGameControllerState() instanceof PopupMenuController);

        this.command.execute();

        Assertions.assertTrue(this.gameController.getGameControllerState() instanceof PopupMenuController);
    }
}
