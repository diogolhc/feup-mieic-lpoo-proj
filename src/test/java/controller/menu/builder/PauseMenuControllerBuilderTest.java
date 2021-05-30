package controller.menu.builder;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.element.ButtonController;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class PauseMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new PauseMenuControllerBuilder(Mockito.mock(GameController.class));
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu(new Position(0, 0));
        Assertions.assertTrue(menuController instanceof PopupMenuController);
        Assertions.assertEquals(2, menuController.getMenu().getButtons().size());
        Assertions.assertTrue(menuController
                .getButtons()
                .stream()
                .anyMatch(controller -> controller.getButton().getTitle().equals("MAIN MENU")));
    }
}
