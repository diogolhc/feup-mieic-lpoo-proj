package controller.menu.element;

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
        this.button1 = new Button(new Position(1, 1), "B1");
        this.button2 = new Button(new Position(1, 5), "B2");
        this.button3 = new Button(new Position(5, 5), "B3");

        this.command1 = Mockito.mock(Command.class);
        this.command2 = Mockito.mock(Command.class);
        this.command3 = Mockito.mock(Command.class);

        this.controller1 = new ButtonController(this.button1, this.command1);
        this.controller2 = new ButtonController(this.button2, this.command2);
        this.controller3 = new ButtonController(this.button3, this.command3);
    }

    @Test
    public void reactMouseMovement() {
        Position position = new Position(0, 0);
        this.controller1.reactMouseMovement(position);
        Assertions.assertFalse(this.button1.isSelected());
        this.controller2.reactMouseMovement(position);
        Assertions.assertFalse(this.button2.isSelected());
        this.controller3.reactMouseMovement(position);
        Assertions.assertFalse(this.button3.isSelected());

        position = new Position(2, 3);
        this.controller1.reactMouseMovement(position);
        Assertions.assertTrue(this.button1.isSelected());
        this.controller2.reactMouseMovement(position);
        Assertions.assertFalse(this.button2.isSelected());
        this.controller3.reactMouseMovement(position);
        Assertions.assertFalse(this.button3.isSelected());

        position = new Position(5, 5);
        this.controller1.reactMouseMovement(position);
        Assertions.assertFalse(this.button1.isSelected());
        this.controller2.reactMouseMovement(position);
        Assertions.assertFalse(this.button2.isSelected());
        this.controller3.reactMouseMovement(position);
        Assertions.assertTrue(this.button3.isSelected());

        // Movement should never cause commands to execute
        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.never()).execute();
        Mockito.verify(this.command3, Mockito.never()).execute();
    }

    @Test
    public void reactMouseClick() {
        Position position = new Position(0, 2);
        this.controller1.reactMouseClick(position);
        Mockito.verify(this.command1, Mockito.never()).execute();
        this.controller2.reactMouseClick(position);
        Mockito.verify(this.command2, Mockito.never()).execute();
        this.controller3.reactMouseClick(position);
        Mockito.verify(this.command3, Mockito.never()).execute();

        position = new Position(1, 3);
        this.controller1.reactMouseClick(position);
        Mockito.verify(this.command1, Mockito.times(1)).execute();
        this.controller2.reactMouseClick(position);
        Mockito.verify(this.command2, Mockito.never()).execute();
        this.controller3.reactMouseClick(position);
        Mockito.verify(this.command3, Mockito.never()).execute();

        position = new Position(5, 7);
        this.controller1.reactMouseClick(position);
        Mockito.verify(this.command1, Mockito.times(1)).execute();
        this.controller2.reactMouseClick(position);
        Mockito.verify(this.command2, Mockito.never()).execute();
        this.controller3.reactMouseClick(position);
        Mockito.verify(this.command3, Mockito.times(1)).execute();
    }

}
