package controller;

import gui.GUI;
import gui.MouseListener;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

import java.io.IOException;

public class GameControllerTest {
    private GUI gui;
    private GameControllerState state;
    private GameViewer viewer;
    private GameController controller;
    private MouseListener mouseListener;

    @BeforeEach
    public void setUp() throws IOException {
        this.gui = Mockito.mock(GUI.class);
        this.state = Mockito.mock(GameControllerState.class);
        this.viewer = Mockito.mock(GameViewer.class);

        Mockito.doAnswer(invocation -> {
            this.mouseListener = invocation.getArgument(0);
            return null;
        }).when(this.gui).setMouseListener(Mockito.any());
        Mockito.when(this.gui.getNextKeyboardAction()).thenReturn(GUI.KEYBOARD_ACTION.NONE);

        Mockito.when(this.state.getViewer()).thenReturn(this.viewer);

        this.controller = new GameController(this.gui, this.state);
    }

    @Test
    public void run() throws IOException {
        Mockito.when(this.gui.getNextKeyboardAction()).thenReturn(
                GUI.KEYBOARD_ACTION.NONE,
                GUI.KEYBOARD_ACTION.NONE,
                GUI.KEYBOARD_ACTION.INTERACT,
                GUI.KEYBOARD_ACTION.MOVE_LEFT,
                GUI.KEYBOARD_ACTION.QUIT);

        this.controller.run();

        Mockito.verify(this.viewer, Mockito.times(5)).drawScreen(this.gui);
        Mockito.verify(this.state, Mockito.times(2)).reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verify(this.state, Mockito.times(1)).reactKeyboard(GUI.KEYBOARD_ACTION.MOVE_LEFT);
        Mockito.verify(this.state, Mockito.never()).reactKeyboard(GUI.KEYBOARD_ACTION.QUIT);
    }

    @Test
    public void runFrame() throws IOException {
        this.controller.runFrame(50);

        Mockito.verify(this.viewer, Mockito.times(1)).drawScreen(this.gui);
        Mockito.verify(this.state, Mockito.times(1)).reactKeyboard(GUI.KEYBOARD_ACTION.NONE);
        Mockito.verify(this.state, Mockito.times(1)).reactTimePassed(50);

        Mockito.when(this.gui.getNextKeyboardAction()).thenReturn(GUI.KEYBOARD_ACTION.INTERACT);
        this.controller.runFrame(250);

        Mockito.verify(this.viewer, Mockito.times(2)).drawScreen(this.gui);
        Mockito.verify(this.state, Mockito.times(1)).reactKeyboard(GUI.KEYBOARD_ACTION.INTERACT);
        Mockito.verify(this.state, Mockito.times(1)).reactTimePassed(250);
    }

    @Test
    public void setStateWhileRunning() {
        this.controller.setRunning(true);
        this.controller.setGameControllerState(Mockito.mock(GameControllerState.class));
        Assertions.assertNotEquals(this.state, this.controller.getGameControllerState());
        Mockito.verify(this.state, Mockito.times(1)).reactChangeState();
        Mockito.verify(this.controller.getGameControllerState(), Mockito.times(1))
                .reactMouseMovement(Mockito.any());
    }

    @Test
    public void setStateWhileNotRunning() {
        this.controller.setGameControllerState(Mockito.mock(GameControllerState.class));
        Assertions.assertNotEquals(this.state, this.controller.getGameControllerState());
        Mockito.verifyNoInteractions(this.state);
    }

    @Test
    public void onMouseMovement() {
        this.controller.setRunning(true);
        this.mouseListener.onMouseMovement(4, 9);
        Mockito.verify(this.state, Mockito.times(1)).reactMouseMovement(new Position(4, 9));
    }

    @Test
    public void onMouseClick() {
        this.controller.setRunning(true);
        this.mouseListener.onMouseClick(5, 7);
        Mockito.verify(this.state, Mockito.times(1)).reactMouseClick(new Position(5, 7));
    }

    @Test
    public void doNotReactToMouseWhileNotRunning() {
        this.mouseListener.onMouseMovement(4, 9);
        this.mouseListener.onMouseClick(5, 7);
        Mockito.verifyNoInteractions(this.state);
    }
}
