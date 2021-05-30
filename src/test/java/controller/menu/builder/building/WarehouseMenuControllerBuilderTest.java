package controller.menu.builder.building;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.building.Edifice;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class WarehouseMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        this.farm = Mockito.mock(Farm.class);
        this.inventory = new Inventory(500);
        Mockito.when(this.farm.getInventory()).thenReturn(this.inventory);
    }

    @Test
    public void buildMenu() {
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
                    return null;
                }
            });
        }

        Mockito.when(this.farm.getAllItems()).thenReturn(items);
        this.inventory.storeItem(items.get(1), 200);

        this.builder = new WarehouseMenuControllerBuilder(Mockito.mock(GameController.class), this.farm, new Edifice("TEST"));
        MenuController menuController = this.builder.buildMenu();
        Assertions.assertEquals("TEST", menuController.getMenu().getTitle());
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("USED SPACE: 200/500")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i9          x0")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i10       x200")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("i11         x0")));
    }

    @Test
    public void buildMenuEmpty() {
        Mockito.when(this.farm.getAllItems()).thenReturn(new ArrayList<>());

        this.builder = new WarehouseMenuControllerBuilder(Mockito.mock(GameController.class), this.farm, new Edifice("TEST"));
        MenuController menuController = this.builder.buildMenu();
        Assertions.assertEquals("TEST", menuController.getMenu().getTitle());
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("USED SPACE: 0/500")));
    }
}
