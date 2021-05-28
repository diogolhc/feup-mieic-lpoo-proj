package controller.command;

import controller.command.farm.crop_field.RemoveCropCommand;
import model.Position;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RemoveCropCommandTest {
    private CropField cropField;
    private ReadyToHarvest stateReady;
    private Planted statePlanted;
    private NotPlanted stateNotPlanted;
    private Command command;

    @BeforeEach
    public void setUp() {
        stateReady = Mockito.mock(ReadyToHarvest.class);
        statePlanted = Mockito.mock(Planted.class);
        stateNotPlanted = Mockito.mock(NotPlanted.class);
        cropField = new CropField(new Position(0, 0));
        command = new RemoveCropCommand(cropField);
    }

    @Test
    public void executeReady() {
        cropField.setState(stateReady);
        command.execute();
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
    }

    @Test
    public void executeNotPlanted() {
        cropField.setState(stateNotPlanted);
        command.execute();
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
    }
}
