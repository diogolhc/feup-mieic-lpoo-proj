package controller.menu.builder.building.crop_field;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CropFieldGrowingMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private Farm farm;
    private GameController gameController;
    private CropField cropField;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        this.farm = Mockito.mock(Farm.class);
        this.cropField = new CropField(new Position(0, 0));
        this.cropField.setState(new CropFieldState() {
            @Override
            public InGameTime getRemainingTime() {
                return new InGameTime(5);
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
        this.builder = new CropFieldGrowingMenuControllerBuilder(this.gameController, this.farm, this.cropField);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("REMOVE CROP")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("CROP: TEST CROP")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("REMAINING TIME: 00:05")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("QUANTITY: 50")));
    }

    @Test
    public void menuChangesWhenReady() {
        MenuController menuController = this.builder.buildMenu();

        menuController.reactTimePassed(5);
        Mockito.verify(this.gameController, Mockito.never()).setGameControllerState(Mockito.any());
        this.cropField.setState(Mockito.mock(ReadyToHarvest.class));
        menuController.reactTimePassed(5);

        ArgumentCaptor< GameControllerState > captor = ArgumentCaptor.forClass(GameControllerState.class);
        Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
        Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
    }
}
