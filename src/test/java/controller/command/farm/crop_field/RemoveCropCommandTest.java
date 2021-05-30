package controller.command.farm.crop_field;

import controller.command.Command;
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
        this.stateReady = Mockito.mock(ReadyToHarvest.class);
        this.statePlanted = Mockito.mock(Planted.class);
        this.stateNotPlanted = Mockito.mock(NotPlanted.class);
        this.cropField = new CropField(new Position(0, 0));
        this.command = new RemoveCropCommand(this.cropField);
    }

    @Test
    public void executeReady() {
        this.cropField.setState(this.stateReady);
        this.command.execute();
        Assertions.assertTrue(this.cropField.getState() instanceof NotPlanted);
    }

    @Test
    public void executeNotPlanted() {
        this.cropField.setState(this.stateNotPlanted);
        this.command.execute();
        Assertions.assertTrue(this.cropField.getState() instanceof NotPlanted);
    }

    @Test
    public void executePlanted() {
        this.cropField.setState(this.statePlanted);
        this.command.execute();
        Assertions.assertTrue(this.cropField.getState() instanceof NotPlanted);
    }
}
