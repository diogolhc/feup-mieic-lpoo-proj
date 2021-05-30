package controller.farm.element.building;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.CompoundCommand;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.controller_state.SetControllerStateCommand;
import controller.farm.FarmDemolishController;
import controller.farm.FarmWithFarmerController;
import controller.menu.builder.building.stockyard.CollectMenuControllerBuilder;
import controller.menu.builder.building.stockyard.FeedAnimalsMenuControllerBuilder;
import controller.menu.builder.building.stockyard.ProducingMenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.building.BuildingSet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.building.stockyard.state.Producing;
import model.farm.building.stockyard.state.ReadyToCollect;
import model.farm.building.stockyard.state.StockyardState;
import model.farm.data.Livestock;
import model.farm.data.Weather;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import model.farm.entity.Animal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StockyardControllerTest {
    private StockyardController stockyardController;
    private Stockyard stockyard;
    private Livestock livestock;
    private GameController gameController;
    private Farm farm;

    @BeforeEach
    public void setUp() {
        this.livestock = Mockito.mock(Livestock.class);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(this.livestock.getFoodCrop()).thenReturn(new Crop("WHEAT"));
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(1);
        AnimalProduct product = Mockito.mock(AnimalProduct.class);
        Mockito.when(this.livestock.getProducedItem()).thenReturn(product);
        Mockito.when(product.getProductionTime()).thenReturn(new InGameTime(0, 2, 0));
        Mockito.when(product.getBaseProducedAmount()).thenReturn(10);

        this.stockyard = new Stockyard(new Position(10, 10), this.livestock);
        this.stockyard.getAnimals().addAnimal(Mockito.mock(Animal.class));
        Mockito.when(this.stockyard.getAnimals().getList().get(0).getIdleTime()).thenReturn(new InGameTime(5));
        this.stockyard.setState(new NotProducing());

        this.gameController = Mockito.mock(GameController.class);
        this.farm = Mockito.mock(Farm.class);
        this.stockyardController = new StockyardController(this.gameController, this.farm);
    }

    @Test
    public void getInteractionCommandNotProducing() {
        this.stockyard.setState(new NotProducing());
        Command command = this.stockyardController.getInteractionCommand(this.stockyard);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof FeedAnimalsMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandProducing() {
        this.stockyard.setState(new Producing(this.stockyard));
        Command command = this.stockyardController.getInteractionCommand(this.stockyard);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof ProducingMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandReadyToCollect() {
        this.stockyard.setState(new ReadyToCollect(this.stockyard, 1));
        Command command = this.stockyardController.getInteractionCommand(this.stockyard);
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof CollectMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandUnknownState() {
        this.stockyard.setState(Mockito.mock(StockyardState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> this.stockyardController.getInteractionCommand(this.stockyard));
    }

    @Test
    public void getDemolishCommand() {
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(FarmDemolishController.class));

        BuildingSet buildings = new BuildingSet();
        buildings.addStockyard(this.stockyard);
        Mockito.when(this.farm.getBuildings()).thenReturn(buildings);

        Command command = this.stockyardController.getDemolishCommand(this.stockyard);
        Assertions.assertTrue(command instanceof CompoundCommand);
        CompoundCommand compoundCommand = (CompoundCommand) command;
        compoundCommand.getCommands().get(0).execute();
        Assertions.assertFalse(buildings.getCropFields().contains(this.stockyard));

        Assertions.assertTrue(compoundCommand.getCommands().get(1) instanceof SetControllerStateCommand);
        SetControllerStateCommand setControllerStateCommand = (SetControllerStateCommand) compoundCommand.getCommands().get(1);
        Assertions.assertTrue(setControllerStateCommand.getControllerState() instanceof FarmWithFarmerController);
    }

    @Test
    public void getDemolishCommandOtherController() {
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));

        BuildingSet buildings = new BuildingSet();
        buildings.addStockyard(this.stockyard);
        Mockito.when(this.farm.getBuildings()).thenReturn(buildings);

        Command command = this.stockyardController.getDemolishCommand(this.stockyard);
        Assertions.assertTrue(command instanceof CompoundCommand);
        CompoundCommand compoundCommand = (CompoundCommand) command;
        compoundCommand.getCommands().get(0).execute();
        Assertions.assertFalse(buildings.getCropFields().contains(this.stockyard));

        Assertions.assertTrue(compoundCommand.getCommands().get(1) instanceof SetControllerStateCommand);
        SetControllerStateCommand setControllerStateCommand = (SetControllerStateCommand) compoundCommand.getCommands().get(1);
        Assertions.assertSame(this.gameController.getGameControllerState(), setControllerStateCommand.getControllerState());
    }

    @Test
    public void reactTimePassed() {
        Mockito.when(this.farm.getCurrentWeather()).thenReturn(Mockito.mock(Weather.class));
        Mockito.when(this.farm.getCurrentWeather().getEffect(Mockito.any())).thenReturn(1.5);
        this.stockyard.setState(new Producing(this.stockyard));
        this.stockyardController.reactTimePassed(this.stockyard, new InGameTime(0, 1, 30));
        Assertions.assertEquals(new InGameTime(0, 0, 30), this.stockyard.getRemainingTime());
        Assertions.assertEquals(11, this.stockyard.getCollectAmount());
        Mockito.verify(this.stockyard.getAnimals().getList().get(0), Mockito.atLeastOnce()).getIdleTime();
    }
}
