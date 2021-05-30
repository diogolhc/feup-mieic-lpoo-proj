package controller.menu.builder.info;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InfoMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @Test
    public void buildMenu() {
        this.builder = new InfoMenuControllerBuilder(Mockito.mock(GameController.class), "TITLE", "MESSAGE TEST");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));
        Assertions.assertTrue(menuController instanceof PopupMenuController);
        Assertions.assertEquals("TITLE", menuController.getMenu().getTitle());
        Assertions.assertEquals(1, menuController.getMenu().getLabels().size());
        Assertions.assertTrue(menuController
                .getMenu()
                .getLabels()
                .stream()
                .anyMatch(label -> label.getString().equals("MESSAGE TEST")));
        Assertions.assertEquals(14, menuController.getMenu().getWidth());
        Assertions.assertEquals(6, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuMultilineMessage() {
        this.builder = new InfoMenuControllerBuilder(Mockito.mock(GameController.class), "TITLE",
                "MESSAGE TEST\nMESSAGE\nTHE BIGGEST LINE OF ALL\nsmall");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(25, menuController.getMenu().getWidth());
        Assertions.assertEquals(9, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuSmallMessage() {
        this.builder = new InfoMenuControllerBuilder(Mockito.mock(GameController.class), "TITLE", "hi");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(10, menuController.getMenu().getWidth());
        Assertions.assertEquals(6, menuController.getMenu().getHeight());
    }

    @Test
    public void buildMenuEmptyMessage() {
        this.builder = new InfoMenuControllerBuilder(Mockito.mock(GameController.class), "TEST", "");
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));

        Assertions.assertEquals(9, menuController.getMenu().getWidth());
        Assertions.assertEquals(6, menuController.getMenu().getHeight());
    }
}
