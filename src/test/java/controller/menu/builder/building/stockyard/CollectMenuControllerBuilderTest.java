package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.building.crop_field.HarvestMenuControllerBuilder;
import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.building.stockyard.state.StockyardState;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
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
        gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getProducedItem().getName()).thenReturn("p1");
        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.setState(new ReadyToCollect(stockyard, 3));

        builder = new CollectMenuControllerBuilder(gameController, Mockito.mock(Inventory.class), stockyard);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("COLLECT")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("PRODUCT: p1")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("QUANTITY: 3")));
    }
}
