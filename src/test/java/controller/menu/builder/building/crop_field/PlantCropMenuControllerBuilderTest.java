package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import gui.Color;
import model.InGameTime;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Wallet;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
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
        this.farm = Mockito.mock(Farm.class);
        this.gameController = Mockito.mock(GameController.class);

        List<Crop> crops = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Crop crop = new Crop("c" + i);
            crop.setPlantPrice(new Currency(100 * i));
            crop.addGrowthStage(new CropGrowthStage(new InGameTime(60+i), '.', Color.BLACK));
            crop.setBaseHarvestAmount(10*i);

            crops.add(crop);
        }

        Mockito.when(this.farm.getCropTypes()).thenReturn(crops);

        this.cropField = Mockito.mock(CropField.class);
        Mockito.when(this.cropField.getState()).thenReturn(Mockito.mock(NotPlanted.class));
        this.builder = new PlantCropMenuControllerBuilder(this.gameController, this.farm, this.cropField);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

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
        Mockito.when(this.farm.getWallet()).thenReturn(new Wallet(new Currency(10)));
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("c0"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(this.cropField, Mockito.times(1)).setState(Mockito.any());
                });
    }

    @Test
    public void cannotAffordBuildable() {
        Mockito.when(this.farm.getWallet()).thenReturn(new Wallet(new Currency(10)));
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("c3"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(this.cropField, Mockito.never()).setState(Mockito.any());
                });
    }
}
