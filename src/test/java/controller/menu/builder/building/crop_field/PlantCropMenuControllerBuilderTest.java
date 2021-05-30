package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.GameControllerState;
import controller.command.CompoundCommand;
import controller.command.farm.crop_field.PlantCropCommand;
import controller.farm.FarmNewBuildingController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.building.WarehouseMenuControllerBuilder;
import gui.Color;
import model.InGameTime;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.building.Edifice;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import model.farm.data.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlantCropMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private CropField cropField;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        farm = Mockito.mock(Farm.class);
        gameController = Mockito.mock(GameController.class);

        List<Crop> crops = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Crop crop = new Crop("c" + i);
            crop.setPlantPrice(new Currency(100 * i));
            crop.addGrowthStage(new CropGrowthStage(new InGameTime(60+i), '.', Color.BLACK));
            crop.setBaseHarvestAmount(10*i);

            crops.add(crop);
        }

        Mockito.when(farm.getCropTypes()).thenReturn(crops);

        cropField = Mockito.mock(CropField.class);
        Mockito.when(cropField.getState()).thenReturn(Mockito.mock(NotPlanted.class));
        builder = new PlantCropMenuControllerBuilder(gameController, farm, cropField);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("c0")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("c1")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("c2")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("01:00  x0")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("01:01 x10")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("01:02 x20")));

    }

    @Test
    public void canAffordCrop() {
        Mockito.when(farm.getWallet()).thenReturn(new Wallet(new Currency(10)));
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("c0"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(cropField, Mockito.times(1)).setState(Mockito.any());
                });
    }

    @Test
    public void cannotAffordBuildable() {
        Mockito.when(farm.getWallet()).thenReturn(new Wallet(new Currency(10)));
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("c3"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(cropField, Mockito.never()).setState(Mockito.any());
                });
    }
}
