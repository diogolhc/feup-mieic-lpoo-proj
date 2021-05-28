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
        stateReady = Mockito.mock(ReadyToHarvest.class);
        Mockito.when(stateReady.getCrop()).thenReturn(Mockito.mock(Crop.class));
        Mockito.when(stateReady.getHarvestAmount()).thenReturn(5);
        statePlanted = Mockito.mock(Planted.class);
        stateNotPlanted = Mockito.mock(NotPlanted.class);
        inventory = Mockito.mock(Inventory.class);

        cropField = new CropField(new Position(0, 0));
        command = new HarvestCropCommand(inventory, cropField);
    }

    @Test
    public void executeReady() {
        cropField.setState(stateReady);
        command.execute();
        Assertions.assertTrue(cropField.getState() instanceof NotPlanted);
        Mockito.verify(inventory, Mockito.times(1))
                .storeItem(stateReady.getCrop(), stateReady.getHarvestAmount());
    }

    @Test
    public void executeNotPlanted() {
        cropField.setState(stateNotPlanted);
        command.execute();
        Assertions.assertSame(stateNotPlanted, cropField.getState());
        Mockito.verifyNoInteractions(inventory);
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertSame(statePlanted, cropField.getState());
        Mockito.verifyNoInteractions(inventory);
    }
}
