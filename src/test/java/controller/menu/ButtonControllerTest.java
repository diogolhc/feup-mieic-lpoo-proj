package controller.menu;

import controller.command.Command;
import model.Position;
import model.menu.Button;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ButtonControllerTest {
    private Button button1;
    private Button button2;
    private Button button3;
    private ButtonController controller;

    @BeforeEach
    public void setUp() {
        button1 = new Button(new Position(1, 1), "B1");
        button1.setCommand(Mockito.mock(Command.class));
        button2 = new Button(new Position(1, 5), "B2");
        button2.setCommand(Mockito.mock(Command.class));
        button3 = new Button(new Position(5, 5), "B3");
        button3.setCommand(Mockito.mock(Command.class));
        controller = new ButtonController();
    }

    @Test
    public void reactMouseMovement() {
        Position position = new Position(0, 0);
        controller.reactMouseMovement(button1, position);
        Assertions.assertFalse(button1.isSelected());
        controller.reactMouseMovement(button2, position);
        Assertions.assertFalse(button2.isSelected());
        controller.reactMouseMovement(button3, position);
        Assertions.assertFalse(button3.isSelected());

        position = new Position(2, 3);
        controller.reactMouseMovement(button1, position);
        Assertions.assertTrue(button1.isSelected());
        controller.reactMouseMovement(button2, position);
        Assertions.assertFalse(button2.isSelected());
        controller.reactMouseMovement(button3, position);
        Assertions.assertFalse(button3.isSelected());

        position = new Position(5, 6);
        controller.reactMouseMovement(button1, position);
        Assertions.assertFalse(button1.isSelected());
        controller.reactMouseMovement(button2, position);
        Assertions.assertFalse(button2.isSelected());
        controller.reactMouseMovement(button3, position);
        Assertions.assertTrue(button3.isSelected());

        // Movement should never cause commands to execute
        Mockito.verify(button1.getCommand(), Mockito.never()).execute();
        Mockito.verify(button2.getCommand(), Mockito.never()).execute();
        Mockito.verify(button3.getCommand(), Mockito.never()).execute();
    }

    @Test
    public void reactMouseClick() {
        Position position = new Position(0, 2);
        controller.reactMouseClick(button1, position);
        Mockito.verify(button1.getCommand(), Mockito.never()).execute();
        controller.reactMouseClick(button2, position);
        Mockito.verify(button2.getCommand(), Mockito.never()).execute();
        controller.reactMouseClick(button3, position);
        Mockito.verify(button3.getCommand(), Mockito.never()).execute();

        position = new Position(1, 3);
        controller.reactMouseClick(button1, position);
        Mockito.verify(button1.getCommand(), Mockito.times(1)).execute();
        controller.reactMouseClick(button2, position);
        Mockito.verify(button2.getCommand(), Mockito.never()).execute();
        controller.reactMouseClick(button3, position);
        Mockito.verify(button3.getCommand(), Mockito.never()).execute();

        position = new Position(5, 7);
        controller.reactMouseClick(button1, position);
        Mockito.verify(button1.getCommand(), Mockito.times(1)).execute();
        controller.reactMouseClick(button2, position);
        Mockito.verify(button2.getCommand(), Mockito.never()).execute();
        controller.reactMouseClick(button3, position);
        Mockito.verify(button3.getCommand(), Mockito.times(1)).execute();
    }
}
