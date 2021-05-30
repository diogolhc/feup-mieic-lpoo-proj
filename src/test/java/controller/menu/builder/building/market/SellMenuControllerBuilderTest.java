package controller.menu.builder.building.market;

import controller.GameController;
import controller.GameControllerState;
import controller.farm.FarmController;
import controller.farm.FarmNewBuildingController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.building.WarehouseMenuControllerBuilder;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.data.Livestock;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SellMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private GameController gameController;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        gameController = Mockito.mock(GameController.class);
        farm = Mockito.mock(Farm.class);
        inventory = new Inventory(50);
        Mockito.when(farm.getInventory()).thenReturn(inventory);

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            items.add(new Item() {
                @Override
                public String getName() {
                    return "i" + (finalI + 9);
                }

                @Override
                public Currency getSellPrice() {
                    return new Currency(finalI*10);
                }
            });
        }

        Mockito.when(farm.getAllItems()).thenReturn(items);
        inventory.storeItem(items.get(1), 30);

        builder = new SellMenuControllerBuilder(gameController, farm);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().size() > farm.getAllItems().size() * 3);

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x1")).count() == farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x10")).count() == farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x100")).count() == farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i9        x0   0C")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i10      x30  10C")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i11       x0  20C")));
    }
}
