package controller.command;

import model.Position;
import model.farm.Inventory;
import model.farm.building.CropField;
import model.farm.building.crop_field_state.NotPlanted;
import model.farm.building.crop_field_state.Planted;
import model.farm.building.crop_field_state.ReadyToHarvest;
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
        statePlanted = Mockito.mock(Planted.class);
        stateNotPlanted = Mockito.mock(NotPlanted.class);
        cropField = new CropField(new Position(0, 0));
        inventory = new Inventory(200);
        command = new HarvestCropCommand(inventory, cropField);
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
        Assertions.assertSame(stateNotPlanted, cropField.getState());
    }

    @Test
    public void executePlanted() {
        cropField.setState(statePlanted);
        command.execute();
        Assertions.assertSame(statePlanted, cropField.getState());
    }

    @Test
    public void executeReadyInventory() {
        cropField.setState(stateReady);
        int harvestAmount = cropField.getHarvestAmount();
        command.execute();
        Assertions.assertEquals(harvestAmount, inventory.getCapacity());
        Assertions.assertEquals(harvestAmount, inventory.getAmount(cropField.getState().getCrop()));
    }
}
