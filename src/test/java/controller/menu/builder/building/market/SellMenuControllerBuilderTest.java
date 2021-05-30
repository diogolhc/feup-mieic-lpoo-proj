package controller.menu.builder.building.market;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class SellMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private GameController gameController;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        this.farm = Mockito.mock(Farm.class);
        this.inventory = new Inventory(50);
        Mockito.when(this.farm.getInventory()).thenReturn(this.inventory);

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

        Mockito.when(this.farm.getAllItems()).thenReturn(items);
        this.inventory.storeItem(items.get(1), 30);

        this.builder = new SellMenuControllerBuilder(this.gameController, this.farm);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().size() > this.farm.getAllItems().size() * 3);

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x1")).count() == this.farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x10")).count() == this.farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().filter(button ->
                button.getTitle().equals("x100")).count() == this.farm.getAllItems().size());

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i9        x0   0C")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i10      x30  10C")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i11       x0  20C")));
    }
}
