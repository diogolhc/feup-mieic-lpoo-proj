package controller.command.controller_state;

import controller.GameController;
import controller.GameControllerState;
import controller.command.controller_state.SetControllerStateCommand;
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
        this.state = Mockito.mock(GameControllerState.class);
        this.gameController = new GameController(Mockito.mock(GUI.class));

        this.command = new SetControllerStateCommand(this.gameController, this.state);
    }

    @Test
    public void execute() {
        Assertions.assertFalse(this.gameController.getGameControllerState() == this.state);

        this.command.execute();

        Assertions.assertTrue(this.gameController.getGameControllerState() == this.state);
    }
}
