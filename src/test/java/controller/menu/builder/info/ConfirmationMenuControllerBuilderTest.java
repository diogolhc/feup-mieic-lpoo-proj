package controller.menu.builder.info;

import controller.GameController;
import controller.command.Command;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.element.ButtonController;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class ConfirmationMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @Test
    public void buildMenu() {
        this.builder = new ConfirmationMenuControllerBuilder(Mockito.mock(GameController.class), "CONFIRM", "YES OR NO?");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertTrue(menuController instanceof PopupMenuController);
        Assertions.assertEquals("CONFIRM", menuController.getMenu().getTitle());
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());
        Assertions.assertEquals(1, menuController.getMenu().getLabels().size());
        Assertions.assertTrue(menuController
                .getMenu()
                .getLabels()
                .stream()
                .anyMatch(label -> label.getString().equals("YES OR NO?")));
        Assertions.assertEquals(12, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuMultilineMessage() {
        this.builder = new ConfirmationMenuControllerBuilder(Mockito.mock(GameController.class), "CONFIRM",
                "MESSAGE TEST\nMESSAGE\nTHE BIGGEST LINE OF ALL\nsmall");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(25, menuController.getMenu().getWidth());
        Assertions.assertEquals(13, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuSmallMessage() {
        this.builder = new ConfirmationMenuControllerBuilder(Mockito.mock(GameController.class), "YES?", "hi");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(12, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuEmptyMessage() {
        this.builder = new ConfirmationMenuControllerBuilder(Mockito.mock(GameController.class), "", "");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(12, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }

    @Test
    public void buttonCommands() {
        GameController gameController = Mockito.mock(GameController.class);
        Command yesCommand = Mockito.mock(Command.class);
        Command noCommand = Mockito.mock(Command.class);
        this.builder = new ConfirmationMenuControllerBuilder(gameController, "CONFIRM", "YES OR NO?")
            .setYesCommand(yesCommand)
            .setNoCommand(noCommand);
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertTrue(menuController instanceof PopupMenuController);
        Assertions.assertEquals("CONFIRM", menuController.getMenu().getTitle());
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());

        Optional<ButtonController> yesButtonControllerOptional = menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("YES"))
                .findAny();
        Assertions.assertTrue(yesButtonControllerOptional.isPresent());

        Optional<ButtonController> noButtonControllerOptional = menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("NO"))
                .findAny();
        Assertions.assertTrue(noButtonControllerOptional.isPresent());

        ButtonController yesButtonController = yesButtonControllerOptional.get();
        ButtonController noButtonController = noButtonControllerOptional.get();

        yesButtonController.getCommand().execute();
        Mockito.verify(yesCommand, Mockito.times(1)).execute();
        Mockito.verifyNoInteractions(noCommand);
        Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(Mockito.any());

        noButtonController.getCommand().execute();
        Mockito.verify(yesCommand, Mockito.times(1)).execute();
        Mockito.verify(noCommand, Mockito.times(1)).execute();
        Mockito.verify(gameController, Mockito.times(2)).setGameControllerState(Mockito.any());
    }
}
