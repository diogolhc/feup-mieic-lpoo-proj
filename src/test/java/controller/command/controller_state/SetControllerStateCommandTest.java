package controller.command.controller_state;

import controller.GameController;
import controller.GameControllerState;
import controller.command.controller_state.SetControllerStateCommand;
import controller.menu.PopupMenuController;
import gui.GUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SetControllerStateCommandTest {
    private GameController gameController;
    private GameControllerState state;
    private SetControllerStateCommand command;

    @BeforeEach
    public void setUp() {
        state = Mockito.mock(GameControllerState.class);
        gameController = new GameController(Mockito.mock(GUI.class));

        command = new SetControllerStateCommand(gameController, state);
    }

    @Test
    public void execute() {
        Assertions.assertFalse(gameController.getGameControllerState() == state);

        command.execute();

        Assertions.assertTrue(gameController.getGameControllerState() == state);
    }
}
