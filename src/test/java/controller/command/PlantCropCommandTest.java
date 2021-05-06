package controller.command;

import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.crop.Crop;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlantCropCommandTest {
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
        cropField = new CropField(new Position(0, 0));
        crop = Mockito.mock(Crop.class);
        command = new PlantCropCommand(cropField, crop);
    }

    @Test
    public void executeReady() {
        cropField.setState(stateReady);
        command.execute();
        Assertions.assertSame(stateReady, cropField.getState());
    }

    @Test
    public void executeNotPlanted() {
        cropField.setState(stateNotPlanted);
        command.execute();
        CropFieldState newState = cropField.getState();
        Assertions.assertTrue(newState instanceof Planted);
        Assertions.assertSame(crop, newState.getCrop());
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertSame(statePlanted, cropField.getState());
    }
}
