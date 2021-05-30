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
        this.stateReady = Mockito.mock(ReadyToHarvest.class);
        this.statePlanted = Mockito.mock(Planted.class);
        this.stateNotPlanted = Mockito.mock(NotPlanted.class);
        this.wallet = Mockito.mock(Wallet.class);

        this.cropField = new CropField(new Position(0, 0));
        this.crop = Mockito.mock(Crop.class);
        Mockito.when(this.crop.getPlantPrice()).thenReturn(new Currency(10));
        this.command = new PlantCropCommand(this.wallet, this.cropField, this.crop);
    }

    @Test
    public void executeReady() {
        this.cropField.setState(this.stateReady);
        this.command.execute();
        Assertions.assertSame(this.stateReady, this.cropField.getState());
        Mockito.verifyNoInteractions(this.wallet);
    }

    @Test
    public void executeNotPlanted() {
        this.cropField.setState(this.stateNotPlanted);
        this.command.execute();
        CropFieldState newState = this.cropField.getState();
        Assertions.assertTrue(newState instanceof Planted);
        Assertions.assertSame(this.crop, newState.getCrop());
        Mockito.verify(this.wallet, Mockito.times(1)).spend(this.crop.getPlantPrice());
    }

    @Test
    public void executePlanted() {
        this.cropField.setState(this.statePlanted);
        this.command.execute();
        Assertions.assertSame(this.statePlanted, this.cropField.getState());
        Mockito.verifyNoInteractions(this.wallet);
    }
}
