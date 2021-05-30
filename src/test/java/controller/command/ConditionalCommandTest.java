package controller.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConditionalCommandTest {
    private ConditionalCommand conditionalCommand;
    private Command command1;
    private Command command2;

    @BeforeEach
    public void setUp() {
        this.command1 = Mockito.mock(Command.class);
        this.command2 = Mockito.mock(Command.class);
    }

    @Test
    public void executeTrue() {
        this.conditionalCommand = new ConditionalCommand(() -> true)
                .ifTrue(this.command1)
                .ifFalse(this.command2);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.times(1)).execute();
        Mockito.verify(this.command2, Mockito.never()).execute();
    }

    @Test
    public void executeFalse() {
        this.conditionalCommand = new ConditionalCommand(() -> false)
                .ifTrue(this.command1)
                .ifFalse(this.command2);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfTrue() {
        this.conditionalCommand = new ConditionalCommand(() -> false);
        this.conditionalCommand
                .ifTrue(this.command1)
                .elseIf(() -> true)
                .ifTrue(this.command2)
                .ifFalse(this.command1);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfFalse() {
        this.conditionalCommand = new ConditionalCommand(() -> false);
        this.conditionalCommand
                .ifTrue(this.command1)
                .elseIf(() -> false)
                .ifTrue(this.command1)
                .ifFalse(this.command2);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfShortcircuit() {
        this.conditionalCommand = new ConditionalCommand(() -> true);
        this.conditionalCommand
                .ifTrue(this.command2)
                .elseIf(() -> false)
                .ifTrue(this.command1)
                .ifFalse(this.command1);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeNestedElseIf() {
        this.conditionalCommand = new ConditionalCommand(() -> false);
        this.conditionalCommand
                .ifTrue(this.command1)
                .elseIf(() -> false)
                .ifTrue(this.command1)
                .elseIf(() -> false)
                .ifTrue(this.command1)
                .elseIf(() -> true)
                .ifTrue(this.command2)
                .elseIf(() -> true)
                .ifTrue(this.command1)
                .elseIf(() -> false)
                .ifTrue(this.command1)
                .ifFalse(this.command1);

        this.conditionalCommand.execute();

        Mockito.verify(this.command1, Mockito.never()).execute();
        Mockito.verify(this.command2, Mockito.times(1)).execute();
    }
}
