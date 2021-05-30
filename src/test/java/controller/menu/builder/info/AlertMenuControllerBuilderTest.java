package controller.menu.builder.info;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AlertMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @Test
    public void buildMenu() {
        this.builder = new AlertMenuControllerBuilder(Mockito.mock(GameController.class), "THIS IS AN ALERT");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertTrue(menuController instanceof PopupMenuController);
        Assertions.assertEquals("ALERT", menuController.getMenu().getTitle());
        Assertions.assertEquals(2, menuController.getMenu().getButtons().size());
        Assertions.assertEquals(1, menuController.getMenu().getLabels().size());
        Assertions.assertTrue(menuController
                .getMenu()
                .getLabels()
                .stream()
                .anyMatch(label -> label.getString().equals("THIS IS AN ALERT")));
        Assertions.assertEquals(18, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuMultilineMessage() {
        this.builder = new AlertMenuControllerBuilder(Mockito.mock(GameController.class),
                "MESSAGE TEST\nMESSAGE\nTHE BIGGEST LINE OF ALL\nsmall");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(25, menuController.getMenu().getWidth());
        Assertions.assertEquals(13, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuSmallMessage() {
        this.builder = new AlertMenuControllerBuilder(Mockito.mock(GameController.class), "hi");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(10, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuEmptyMessage() {
        this.builder = new AlertMenuControllerBuilder(Mockito.mock(GameController.class),  "");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(10, menuController.getMenu().getWidth());
        Assertions.assertEquals(10, menuController.getMenu().getHeight());
    }
}
