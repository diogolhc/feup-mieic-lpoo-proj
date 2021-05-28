package controller.command.farm.crop_field;

import controller.command.Command;
import model.Position;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.crop_field.CropField;
import model.farm.data.item.Crop;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlantCropCommandTest {
    private Wallet wallet;
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
        wallet = Mockito.mock(Wallet.class);

        cropField = new CropField(new Position(0, 0));
        crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getPlantPrice()).thenReturn(new Currency(10));
        command = new PlantCropCommand(wallet, cropField, crop);
    }

    @Test
    public void executeReady() {
        cropField.setState(stateReady);
        command.execute();
        Assertions.assertSame(stateReady, cropField.getState());
        Mockito.verifyNoInteractions(wallet);
    }

    @Test
    public void executeNotPlanted() {
        cropField.setState(stateNotPlanted);
        command.execute();
        CropFieldState newState = cropField.getState();
        Assertions.assertTrue(newState instanceof Planted);
        Assertions.assertSame(crop, newState.getCrop());
        Mockito.verify(wallet, Mockito.times(1)).spend(crop.getPlantPrice());
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertSame(statePlanted, cropField.getState());
        Mockito.verifyNoInteractions(wallet);
    }
}
