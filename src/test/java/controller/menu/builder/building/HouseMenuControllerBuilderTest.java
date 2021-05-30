package controller.menu.builder.building;

import controller.GameController;
import controller.farm.FarmController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.building.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HouseMenuControllerBuilderTest {
    MenuControllerBuilder builder;

    @BeforeEach
    public void setUp() {
        this.builder = new HouseMenuControllerBuilder(
                Mockito.mock(GameController.class),
                Mockito.mock(FarmController.class),
                new Edifice("TEST"),
                15);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));
        Assertions.assertEquals("TEST", menuController.getMenu().getTitle());
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());
        Assertions.assertTrue(menuController
                .getButtons()
                .stream()
                .anyMatch(buttonController -> buttonController.getButton().getTitle().equals("REST")));
        Assertions.assertTrue(menuController
                .getButtons()
                .stream()
                .anyMatch(buttonController -> buttonController.getButton().getTitle().equals("SAVE GAME")));
    }
}
