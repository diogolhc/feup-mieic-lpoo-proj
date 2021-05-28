package controller.menu;

import controller.command.Command;
import controller.menu.element.ButtonController;
import model.Position;
import model.menu.Button;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ButtonControllerTest {
    private ButtonController controller1;
    private ButtonController controller2;
    private ButtonController controller3;
    private Button button1;
    private Button button2;
    private Button button3;
    private Command command1;
    private Command command2;
    private Command command3;

    @BeforeEach
    public void setUp() {
        button1 = new Button(new Position(1, 1), "B1");
        button2 = new Button(new Position(1, 5), "B2");
        button3 = new Button(new Position(5, 5), "B3");

        command1 = Mockito.mock(Command.class);
        command2 = Mockito.mock(Command.class);
        command3 = Mockito.mock(Command.class);

        controller1 = new ButtonController(button1, command1);
        controller2 = new ButtonController(button2, command2);
        controller3 = new ButtonController(button3, command3);
    }

    @Test
    public void reactMouseMovement() {
        Position position = new Position(0, 0);
        controller1.reactMouseMovement(position);
        Assertions.assertFalse(button1.isSelected());
        controller2.reactMouseMovement(position);
        Assertions.assertFalse(button2.isSelected());
        controller3.reactMouseMovement(position);
        Assertions.assertFalse(button3.isSelected());

        position = new Position(2, 3);
        controller1.reactMouseMovement(position);
        Assertions.assertTrue(button1.isSelected());
        controller2.reactMouseMovement(position);
        Assertions.assertFalse(button2.isSelected());
        controller3.reactMouseMovement(position);
        Assertions.assertFalse(button3.isSelected());

        position = new Position(5, 6);
        controller1.reactMouseMovement(position);
        Assertions.assertFalse(button1.isSelected());
        controller2.reactMouseMovement(position);
        Assertions.assertFalse(button2.isSelected());
        controller3.reactMouseMovement(position);
        Assertions.assertTrue(button3.isSelected());

        // Movement should never cause commands to execute
        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.never()).execute();
        Mockito.verify(command3, Mockito.never()).execute();
    }

    @Test
    public void reactMouseClick() {
        Position position = new Position(0, 2);
        controller1.reactMouseClick(position);
        Mockito.verify(command1, Mockito.never()).execute();
        controller2.reactMouseClick(position);
        Mockito.verify(command2, Mockito.never()).execute();
        controller3.reactMouseClick(position);
        Mockito.verify(command3, Mockito.never()).execute();

        position = new Position(1, 3);
        controller1.reactMouseClick(position);
        Mockito.verify(command1, Mockito.times(1)).execute();
        controller2.reactMouseClick(position);
        Mockito.verify(command2, Mockito.never()).execute();
        controller3.reactMouseClick(position);
        Mockito.verify(command3, Mockito.never()).execute();

        position = new Position(5, 7);
        controller1.reactMouseClick(position);
        Mockito.verify(command1, Mockito.times(1)).execute();
        controller2.reactMouseClick(position);
        Mockito.verify(command2, Mockito.never()).execute();
        controller3.reactMouseClick(position);
        Mockito.verify(command3, Mockito.times(1)).execute();
    }

}
