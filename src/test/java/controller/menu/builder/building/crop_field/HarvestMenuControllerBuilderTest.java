package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.menu.MenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.building.WarehouseMenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.data.item.Crop;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class HarvestMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private CropField cropField;

    @BeforeEach
    public void setUp() {
        cropField = new CropField(new Position(0, 0));
        cropField.setState(new CropFieldState() {
            @Override
            public InGameTime getRemainingTime() {
                return new InGameTime(0);
            }

            @Override
            public void setRemainingTime(InGameTime time) { }

            @Override
            public int getHarvestAmount() {
                return 50;
            }

            @Override
            public void changeHarvestAmount(double harvestAmount) { }

            @Override
            public Crop getCrop() {
                return new Crop("TEST CROP");
            }
        });
        builder = new HarvestMenuControllerBuilder(Mockito.mock(GameController.class), Mockito.mock(Inventory.class), cropField);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("HARVEST")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("CROP: TEST CROP")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("QUANTITY: 50")));
    }
}
