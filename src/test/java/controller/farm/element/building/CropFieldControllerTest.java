package controller.farm.element.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.building.crop_field.CropFieldGrowingMenuControllerBuilder;
import controller.menu.builder.building.crop_field.HarvestMenuControllerBuilder;
import controller.menu.builder.building.crop_field.PlantCropMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.crop_field.CropField;
import model.farm.data.Weather;
import model.farm.data.item.Crop;
import model.farm.building.crop_field.state.CropFieldState;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.crop_field.state.Planted;
import model.farm.building.crop_field.state.ReadyToHarvest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CropFieldControllerTest {
    private CropField cropField;
    private CropFieldController cropFieldController;
    private GameController gameController;
    private Farm farm;

    @BeforeEach
    public void setUp() {
        this.cropField = new CropField(new Position(10, 10));
        this.gameController = Mockito.mock(GameController.class);
        this.farm = Mockito.mock(Farm.class);
        this.cropFieldController = new CropFieldController(this.gameController, this.farm);
    }

    @Test
    public void getInteractionCommandNotPlanted() {
        this.cropField.setState(new NotPlanted());
        Command command = this.cropFieldController.getInteractionCommand(this.cropField);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof PlantCropMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandPlanted() {
        this.cropField.setState(new Planted(this.cropField, Mockito.mock(Crop.class)));
        Command command = this.cropFieldController.getInteractionCommand(this.cropField);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof CropFieldGrowingMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandReadyToHarvest() {
        this.cropField.setState(new ReadyToHarvest(this.cropField, Mockito.mock(Crop.class), 1));
        Command command = this.cropFieldController.getInteractionCommand(this.cropField);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof HarvestMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandUnknownState() {
        this.cropField.setState(Mockito.mock(CropFieldState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> this.cropFieldController.getInteractionCommand(this.cropField));
    }

    @Test
    public void getDemolishCommand() {
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(FarmDemolishController.class));

        BuildingSet buildings = new BuildingSet();
        buildings.addCropField(this.cropField);
        buildings.addCropField(Mockito.mock(CropField.class));
        buildings.addCropField(Mockito.mock(CropField.class));
        Mockito.when(this.farm.getBuildings()).thenReturn(buildings);

        Command command = this.cropFieldController.getDemolishCommand(this.cropField);
        Assertions.assertTrue(command instanceof CompoundCommand);
        CompoundCommand compoundCommand = (CompoundCommand) command;
        compoundCommand.getCommands().get(0).execute();
        Assertions.assertFalse(buildings.getCropFields().contains(this.cropField));

        Assertions.assertTrue(compoundCommand.getCommands().get(1) instanceof SetControllerStateCommand);
        SetControllerStateCommand setControllerStateCommand = (SetControllerStateCommand) compoundCommand.getCommands().get(1);
        Assertions.assertTrue(setControllerStateCommand.getControllerState() instanceof FarmWithFarmerController);
    }

    @Test
    public void getDemolishCommandOtherController() {
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));

        BuildingSet buildings = new BuildingSet();
        buildings.addCropField(this.cropField);
        buildings.addCropField(Mockito.mock(CropField.class));
        buildings.addCropField(Mockito.mock(CropField.class));
        Mockito.when(this.farm.getBuildings()).thenReturn(buildings);

        Command command = this.cropFieldController.getDemolishCommand(this.cropField);
        Assertions.assertTrue(command instanceof CompoundCommand);
        CompoundCommand compoundCommand = (CompoundCommand) command;
        compoundCommand.getCommands().get(0).execute();
        Assertions.assertFalse(buildings.getCropFields().contains(this.cropField));

        Assertions.assertTrue(compoundCommand.getCommands().get(1) instanceof SetControllerStateCommand);
        SetControllerStateCommand setControllerStateCommand = (SetControllerStateCommand) compoundCommand.getCommands().get(1);
        Assertions.assertSame(this.gameController.getGameControllerState(), setControllerStateCommand.getControllerState());
    }

    @Test
    public void getDemolishCommandSingleCropField() {
        BuildingSet buildings = new BuildingSet();
        buildings.addCropField(this.cropField);
        Mockito.when(this.farm.getBuildings()).thenReturn(buildings);

        Command command = this.cropFieldController.getDemolishCommand(this.cropField);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof AlertMenuControllerBuilder);
    }

    @Test
    public void reactTimePassed() {
        Crop crop = Mockito.mock(Crop.class);
        Mockito.when(crop.getGrowTime()).thenReturn(new InGameTime(0, 2, 0));
        Mockito.when(crop.getBaseHarvestAmount()).thenReturn(1);
        Mockito.when(this.farm.getCurrentWeather()).thenReturn(Mockito.mock(Weather.class));
        Mockito.when(this.farm.getCurrentWeather().getEffect(Mockito.any())).thenReturn(1.5);
        this.cropField.setState(new Planted(this.cropField, crop));
        this.cropFieldController.reactTimePassed(this.cropField, new InGameTime(0, 1, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 30), this.cropField.getRemainingTime());
        Assertions.assertEquals(2, this.cropField.getHarvestAmount());
    }
}
