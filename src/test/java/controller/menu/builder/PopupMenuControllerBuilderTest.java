package controller.menu.builder;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import model.Position;
import model.menu.Button;
import model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import viewer.GameViewer;

import java.util.Optional;

public class PopupMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new PopupMenuControllerBuilder(Mockito.mock(GameController.class)) {
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
                return 5;
            }
        };
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu(new Position(0, 0));
        Assertions.assertTrue(menuController instanceof PopupMenuController);

        Optional<Button> optionalButton = menuController.getMenu().getButtons().stream().findFirst();
        Assertions.assertTrue(optionalButton.isPresent());
        Assertions.assertEquals(
                menuController.getMenu().getWidth(),
                optionalButton.get().getTopLeft().getX() + optionalButton.get().getWidth());

        Assertions.assertEquals(1, menuController.getMenu().getButtons().size());
    }
}
