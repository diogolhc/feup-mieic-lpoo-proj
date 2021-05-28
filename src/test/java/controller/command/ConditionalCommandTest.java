package controller.command;

import controller.GameController;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.farm.stockyard.SellAnimalCommand;
import controller.menu.MenuController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import gui.GUI;
import model.Position;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class ConditionalCommandTest {
    private ConditionalCommand conditionalCommand;
    private Command command1;
    private Command command2;

    @BeforeEach
    public void setUp() {
        command1 = Mockito.mock(Command.class);
        command2 = Mockito.mock(Command.class);
    }

    @Test
    public void executeTrue() {
        conditionalCommand = new ConditionalCommand(() -> true)
                .ifTrue(command1)
                .ifFalse(command2);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.times(1)).execute();
        Mockito.verify(command2, Mockito.never()).execute();
    }

    @Test
    public void executeFalse() {
        conditionalCommand = new ConditionalCommand(() -> false)
                .ifTrue(command1)
                .ifFalse(command2);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfTrue() {
        conditionalCommand = new ConditionalCommand(() -> false);
        conditionalCommand
                .ifTrue(command1)
                .elseIf(() -> true)
                .ifTrue(command2)
                .ifFalse(command1);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfFalse() {
        conditionalCommand = new ConditionalCommand(() -> false);
        conditionalCommand
                .ifTrue(command1)
                .elseIf(() -> false)
                .ifTrue(command1)
                .ifFalse(command2);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeElseIfShortcircuit() {
        conditionalCommand = new ConditionalCommand(() -> true);
        conditionalCommand
                .ifTrue(command2)
                .elseIf(() -> false)
                .ifTrue(command1)
                .ifFalse(command1);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.times(1)).execute();
    }

    @Test
    public void executeNestedElseIf() {
        conditionalCommand = new ConditionalCommand(() -> false);
        conditionalCommand
                .ifTrue(command1)
                .elseIf(() -> false)
                .ifTrue(command1)
                .elseIf(() -> false)
                .ifTrue(command1)
                .elseIf(() -> true)
                .ifTrue(command2)
                .elseIf(() -> true)
                .ifTrue(command1)
                .elseIf(() -> false)
                .ifTrue(command1)
                .ifFalse(command1);

        conditionalCommand.execute();

        Mockito.verify(command1, Mockito.never()).execute();
        Mockito.verify(command2, Mockito.times(1)).execute();
    }
}
