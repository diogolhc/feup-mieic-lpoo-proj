package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.Inventory;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CollectMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private GameController gameController;
    private Stockyard stockyard;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getProducedItem().getName()).thenReturn("p1");
        this.stockyard = new Stockyard(new Position(0, 0), livestock);
        this.stockyard.setState(new ReadyToCollect(this.stockyard, 3));

        this.builder = new CollectMenuControllerBuilder(this.gameController, Mockito.mock(Inventory.class), this.stockyard);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("COLLECT")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("PRODUCT: p1")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("QUANTITY: 3")));
    }
}
