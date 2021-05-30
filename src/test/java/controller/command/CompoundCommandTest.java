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
        this.command1 = Mockito.mock(Command.class);
        this.command2 = Mockito.mock(Command.class);
        this.command3 = Mockito.mock(Command.class);

        this.compoundCommand1 = new CompoundCommand();
        this.compoundCommand1.addCommand(this.command1);
        this.compoundCommand1.addCommand(this.command2);

        this.compoundCommand2 = new CompoundCommand();

        this.compoundCommand3 = new CompoundCommand();
        this.compoundCommand3.addCommand(this.command3);
        this.compoundCommand3.addCommand(this.compoundCommand1);
        this.compoundCommand3.addCommand(this.compoundCommand2);
        this.compoundCommand3.addCommand(this.command1);
    }

    @Test
    public void executeFlat() {
        this.compoundCommand1.execute();
        InOrder verifier = Mockito.inOrder(this.command1, this.command2, this.command3);
        verifier.verify(this.command1).execute();
        verifier.verify(this.command2).execute();
        verifier.verifyNoMoreInteractions();
    }

    @Test
    public void executeNested() {
        this.compoundCommand3.execute();
        InOrder verifier = Mockito.inOrder(this.command1, this.command2, this.command3);
        verifier.verify(this.command3).execute();
        verifier.verify(this.command1).execute();
        verifier.verify(this.command2).execute();
        verifier.verify(this.command1).execute();
        verifier.verifyNoMoreInteractions();
    }
}
