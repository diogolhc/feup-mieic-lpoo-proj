package controller.menu.builder;

import controller.GameController;
import controller.menu.MainMenuController;
import controller.menu.MenuController;
import model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MainMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        GameController gameController = Mockito.mock(GameController.class);
        this.builder = new MainMenuControllerBuilder(gameController);
        Mockito.when(gameController.getWindowHeight()).thenReturn(15);
        Mockito.when(gameController.getWindowWidth()).thenReturn(20);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));
        Assertions.assertTrue(menuController instanceof MainMenuController);
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());
        Assertions.assertEquals(15, menuController.getMenu().getHeight());
        Assertions.assertEquals(20, menuController.getMenu().getWidth());
    }
}
