package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.InGameTime;
import model.Position;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class FeedAnimalsMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private GameController gameController;
    private Farm farm;
    private Stockyard stockyard;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        Mockito.when(this.gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        this.farm = Mockito.mock(Farm.class);
        Mockito.when(this.farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(this.farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(9);
        this.livestock = Mockito.mock(Livestock.class);
        Mockito.when(this.livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(this.livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(this.livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(this.livestock.getFoodCrop()).thenReturn(new Crop("c1"));
        Mockito.when(this.livestock.getRequiredFood()).thenReturn(10);
        Mockito.when(this.livestock.getProducedItem().getName()).thenReturn("p1");
        Mockito.when(this.livestock.getAnimalName()).thenReturn("ANIMAL");
        Mockito.when(this.livestock.getProducedItem().getBaseProducedAmount()).thenReturn(3);
        Mockito.when(this.livestock.getProducedItem().getProductionTime()).thenReturn(new InGameTime(7));
    }

    @Test
    public void buildMenu() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        this.stockyard.getAnimals().addAnimal(new Position(0, 0));
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);

        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("BUY")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("SELL")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("FEED")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("ANIMALS: 1/5")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("c1: x9")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("NEEDS")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("c1 x10")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("PRODUCES: p1 x3 IN 00:07")));

    }

    @Test
    public void buildMenuNoAnimals() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);

        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("BUY")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("SELL")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("FEED")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("ANIMALS: 0/5")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("c1: x9")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("NEEDS")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("ANIMALS")));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("STOCKYARD IS EMPTY")));

    }

    @Test
    public void sellAnimal() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        this.stockyard.getAnimals().addAnimal(new Position(0, 0));
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("SELL"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(this.farm.getWallet(), Mockito.times(1)).receive(Mockito.any());
                });
    }

    @Test
    public void sellAnimalEmpty() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("SELL"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(this.farm.getWallet());
                });
    }

    @Test
    public void buyAnimal() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(this.farm.getWallet().canBuy(Mockito.any())).thenReturn(true);
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(this.farm.getWallet(), Mockito.times(1)).spend(Mockito.any());
                });
    }

    @Test
    public void buyAnimalNotEnoughMoney() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(this.farm.getWallet().canBuy(Mockito.any())).thenReturn(false);
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(this.farm.getWallet(), Mockito.never()).spend(Mockito.any());
                });
    }

    @Test
    public void buyAnimalFull() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(1);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(this.farm.getWallet().canBuy(Mockito.any())).thenReturn(true);
        this.stockyard.getAnimals().addAnimal(new Position(0, 0));
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(this.farm.getWallet());
                });
    }

    @Test
    public void feedAnimals() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(this.farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(50);
        this.stockyard.getAnimals().addAnimal(new Position(0, 0));
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(this.farm.getInventory(), Mockito.times(1)).removeItem(Mockito.any(), Mockito.anyInt());
                });
    }

    @Test
    public void feedAnimalsEmpty() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        Mockito.when(this.farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(this.farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(50);
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(this.farm.getInventory());
                });
    }

    @Test
    public void feedAnimalNotEnoughFood() {
        Mockito.when(this.livestock.getMaxNumAnimals()).thenReturn(5);
        this.stockyard = new Stockyard(new Position(0, 0), this.livestock);
        this.stockyard.getAnimals().addAnimal(new Position(0, 0));
        Mockito.when(this.farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(this.farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(0);
        this.builder = new FeedAnimalsMenuControllerBuilder(this.gameController, this.farm, this.stockyard);
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(this.farm.getInventory(), Mockito.never()).removeItem(Mockito.any(), Mockito.anyInt());
                });
    }
}
