package controller.menu.builder;

import controller.GameController;
import controller.menu.MenuController;
import model.Position;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

public class MenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        this.builder = new MenuControllerBuilder() {
            @Override
            protected String getTitle() {
                return "TEST";
            }

            @Override
            protected int getWidth() {
                return 5;
            }

            @Override
            protected int getHeight() {
                return 3;
            }

            @Override
            protected MenuController getMenuController(Menu menu) {
                return new MenuController(menu, Mockito.mock(GameController.class)) {
                    @Override
                    public void reactTimePassed(long elapsedTimeSinceLastFrame) { }

                    @Override
                    public GameViewer getViewer() {
                        return null;
                    }
                };
            }
        };
    }

    @Test
    public void buildMenu() {
        Menu menu = this.builder.buildMenu(new Position(4, 7)).getMenu();
        Assertions.assertEquals("TEST", menu.getTitle());
        Assertions.assertEquals(5, menu.getWidth());
        Assertions.assertEquals(3, menu.getHeight());
        Assertions.assertEquals(MenuControllerBuilder.DEFAULT_MENU_COLOR, menu.getColor());
        Assertions.assertEquals(new Position(4, 7), menu.getTopLeftPosition());
        Assertions.assertEquals(0, menu.getButtons().size());
        Assertions.assertEquals(0, menu.getLabels().size());
    }

    @Test
    public void buildMenuCentered() {
        Menu menu = this.builder.buildMenuCentered(20, 25).getMenu();
        Assertions.assertEquals(new Position(7, 11), menu.getTopLeftPosition());
        menu = this.builder.buildMenuCentered(15, 20).getMenu();
        Assertions.assertEquals(new Position(5, 8), menu.getTopLeftPosition());
    }
}
