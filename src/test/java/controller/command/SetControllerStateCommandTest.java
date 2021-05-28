package controller.command;

import controller.GameController;
import controller.GameControllerState;
import controller.command.controller_state.SetControllerStateCommand;
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
        gameController = Mockito.mock(GameController.class);

        command = new SetControllerStateCommand(gameController, state);
    }

    @Test
    public void execute() {
        command.execute();
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(state);
    }
}
