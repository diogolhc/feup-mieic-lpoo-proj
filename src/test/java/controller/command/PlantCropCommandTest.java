package controller.command;

import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.building.CropField;
import model.farm.data.item.Crop;
import model.farm.building.crop_field_state.CropFieldState;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.crop_field_state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlantCropCommandTest {
    private Farm farm;
    private CropField cropField;
    private ReadyToHarvest stateReady;
    private Planted statePlanted;
    private NotPlanted stateNotPlanted;
    private Command command;
    private Crop crop;

    @BeforeEach
    public void setUp() {
        stateReady = Mockito.mock(ReadyToHarvest.class);
        statePlanted = Mockito.mock(Planted.class);
        stateNotPlanted = Mockito.mock(NotPlanted.class);
        farm = new Farm(10,10);
        farm.setCurrency(new Currency(100));
        cropField = new CropField(new Position(0, 0));
        crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getPlantPrice()).thenReturn(new Currency(10));
        command = new PlantCropCommand(farm, cropField, crop);
    }

    @Test
    public void executeReady() {
        cropField.setState(stateReady);
        command.execute();
        Assertions.assertSame(stateReady, cropField.getState());
        Assertions.assertEquals(100, farm.getCurrency().getCoins());
    }

    @Test
    public void executeNotPlanted() {
        cropField.setState(stateNotPlanted);
        command.execute();
        CropFieldState newState = cropField.getState();
        Assertions.assertTrue(newState instanceof Planted);
        Assertions.assertSame(crop, newState.getCrop());
        Assertions.assertEquals(90, farm.getCurrency().getCoins());
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertSame(statePlanted, cropField.getState());
        Assertions.assertEquals(100, farm.getCurrency().getCoins());
    }
}
