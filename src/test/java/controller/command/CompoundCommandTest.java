package controller.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class CompoundCommandTest {
    private CompoundCommand compoundCommand1;
    private CompoundCommand compoundCommand2;
    private CompoundCommand compoundCommand3;
    private Command command1;
    private Command command2;
    private Command command3;

    @BeforeEach
    public void setUp() {
        command1 = Mockito.mock(Command.class);
        command2 = Mockito.mock(Command.class);
        command3 = Mockito.mock(Command.class);

        compoundCommand1 = new CompoundCommand();
        compoundCommand1.addCommand(command1);
        compoundCommand1.addCommand(command2);

        compoundCommand2 = new CompoundCommand();

        compoundCommand3 = new CompoundCommand();
        compoundCommand3.addCommand(command3);
        compoundCommand3.addCommand(compoundCommand1);
        compoundCommand3.addCommand(compoundCommand2);
        compoundCommand3.addCommand(command1);
    }

    @Test
    public void executeFlat() {
        compoundCommand1.execute();
        InOrder verifier = Mockito.inOrder(command1, command2, command3);
        verifier.verify(command1).execute();
        verifier.verify(command2).execute();
        verifier.verifyNoMoreInteractions();
    }

    @Test
    public void executeNested() {
        compoundCommand3.execute();
        InOrder verifier = Mockito.inOrder(command1, command2, command3);
        verifier.verify(command3).execute();
        verifier.verify(command1).execute();
        verifier.verify(command2).execute();
        verifier.verify(command1).execute();
        verifier.verifyNoMoreInteractions();
    }
}
