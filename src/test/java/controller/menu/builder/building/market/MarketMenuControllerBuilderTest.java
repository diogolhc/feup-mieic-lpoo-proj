package controller.menu.builder.building.market;

import controller.GameController;
import controller.farm.FarmController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.Farm;
import model.farm.building.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MarketMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private FarmController farmController;

    @BeforeEach
    public void setUp() {
        this.farmController = Mockito.mock(FarmController.class);
        Mockito.when(this.farmController.getFarm()).thenReturn(Mockito.mock(Farm.class));

        this.builder = new MarketMenuControllerBuilder(
                Mockito.mock(GameController.class),
                this.farmController,
                new Edifice("TEST"));
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu(new Position(0, 0));
        Assertions.assertEquals("TEST", menuController.getMenu().getTitle());
        Assertions.assertEquals(3, menuController.getMenu().getButtons().size());
        Assertions.assertTrue(menuController
                .getButtons()
                .stream()
                .anyMatch(buttonController -> buttonController.getButton().getTitle().equals("BUILD")));
        Assertions.assertTrue(menuController
                .getButtons()
                .stream()
                .anyMatch(buttonController -> buttonController.getButton().getTitle().equals("SELL")));
    }
}
