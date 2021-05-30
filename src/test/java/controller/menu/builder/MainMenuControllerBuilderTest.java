package controller.menu.builder;

import controller.GameController;
import controller.menu.MainMenuController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import model.Position;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

public class MainMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        GameController gameController = Mockito.mock(GameController.class);
        builder = new MainMenuControllerBuilder(gameController);
        Mockito.when(gameController.getWindowHeight()).thenReturn(15);
        Mockito.when(gameController.getWindowWidth()).thenReturn(20);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu(new Position(0, 0));
        Assertions.assertTrue(menuController instanceof MainMenuController);
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());
        Assertions.assertEquals(15, menuController.getMenu().getHeight());
        Assertions.assertEquals(20, menuController.getMenu().getWidth());
    }
}
