package controller.command.farm.crop_field;

import controller.command.Command;
import model.Position;
import model.farm.Inventory;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HarvestCropCommandTest {
    private CropField cropField;
    private ReadyToHarvest stateReady;
    private Planted statePlanted;
    private NotPlanted stateNotPlanted;
    private Command command;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        this.stateReady = Mockito.mock(ReadyToHarvest.class);
        Mockito.when(this.stateReady.getCrop()).thenReturn(Mockito.mock(Crop.class));
        Mockito.when(this.stateReady.getHarvestAmount()).thenReturn(5);
        this.statePlanted = Mockito.mock(Planted.class);
        this.stateNotPlanted = Mockito.mock(NotPlanted.class);
        this.inventory = Mockito.mock(Inventory.class);

        this.cropField = new CropField(new Position(0, 0));
        this.command = new HarvestCropCommand(this.inventory, this.cropField);
    }

    @Test
    public void executeReady() {
        this.cropField.setState(this.stateReady);
        this.command.execute();
        Assertions.assertTrue(this.cropField.getState() instanceof NotPlanted);
        Mockito.verify(this.inventory, Mockito.times(1))
                .storeItem(this.stateReady.getCrop(), this.stateReady.getHarvestAmount());
    }

    @Test
    public void executeNotPlanted() {
        this.cropField.setState(this.stateNotPlanted);
        this.command.execute();
        Assertions.assertSame(this.stateNotPlanted, this.cropField.getState());
        Mockito.verifyNoInteractions(this.inventory);
    }

    @Test
    public void executePlanted() {
        this.cropField.setState(this.statePlanted);
        this.command.execute();
        Assertions.assertSame(this.statePlanted, this.cropField.getState());
        Mockito.verifyNoInteractions(this.inventory);
    }
}
