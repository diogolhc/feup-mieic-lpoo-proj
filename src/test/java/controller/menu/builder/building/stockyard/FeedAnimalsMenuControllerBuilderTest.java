package controller.menu.builder.building.stockyard;

import controller.GameController;
import controller.GameControllerState;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import controller.menu.builder.building.crop_field.PlantCropMenuControllerBuilder;
import gui.Color;
import model.InGameTime;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Inventory;
import model.farm.Wallet;
import model.farm.building.crop_field.CropField;
import model.farm.building.crop_field.state.NotPlanted;
import model.farm.building.stockyard.Stockyard;
import model.farm.data.Livestock;
import model.farm.data.item.AnimalProduct;
import model.farm.data.item.Crop;
import model.farm.data.item.CropGrowthStage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class FeedAnimalsMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private GameController gameController;
    private Farm farm;
    private Stockyard stockyard;
    private Livestock livestock;

    @BeforeEach
    public void setUp() {
        gameController = Mockito.mock(GameController.class);
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        farm = Mockito.mock(Farm.class);
        Mockito.when(farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(9);
        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getProducedItem()).thenReturn(Mockito.mock(AnimalProduct.class));
        Mockito.when(livestock.getFoodCrop()).thenReturn(new Crop("c1"));
        Mockito.when(livestock.getRequiredFood()).thenReturn(10);
        Mockito.when(livestock.getProducedItem().getName()).thenReturn("p1");
        Mockito.when(livestock.getAnimalName()).thenReturn("ANIMAL");
        Mockito.when(livestock.getProducedItem().getBaseProducedAmount()).thenReturn(3);
        Mockito.when(livestock.getProducedItem().getProductionTime()).thenReturn(new InGameTime(7));
    }

    @Test
    public void buildMenu() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);

        MenuController menuController = builder.buildMenu();

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
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);

        MenuController menuController = builder.buildMenu();

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
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("SELL"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(farm.getWallet(), Mockito.times(1)).receive(Mockito.any());
                });
    }

    @Test
    public void sellAnimalEmpty() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("SELL"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(farm.getWallet());
                });
    }

    @Test
    public void buyAnimal() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(farm.getWallet().canBuy(Mockito.any())).thenReturn(true);
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(farm.getWallet(), Mockito.times(1)).spend(Mockito.any());
                });
    }

    @Test
    public void buyAnimalNotEnoughMoney() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(farm.getWallet().canBuy(Mockito.any())).thenReturn(false);
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(farm.getWallet(), Mockito.never()).spend(Mockito.any());
                });
    }

    @Test
    public void buyAnimalFull() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(1);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getWallet()).thenReturn(Mockito.mock(Wallet.class));
        Mockito.when(farm.getWallet().canBuy(Mockito.any())).thenReturn(true);
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("BUY"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(farm.getWallet());
                });
    }

    @Test
    public void feedAnimals() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(50);
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    Mockito.verify(farm.getInventory(), Mockito.times(1)).removeItem(Mockito.any(), Mockito.anyInt());
                });
    }

    @Test
    public void feedAnimalsEmpty() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        Mockito.when(farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(50);
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verifyNoInteractions(farm.getInventory());
                });
    }

    @Test
    public void feedAnimalNotEnoughFood() {
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);
        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.getAnimals().addAnimal(new Position(0, 0));
        Mockito.when(farm.getInventory()).thenReturn(Mockito.mock(Inventory.class));
        Mockito.when(farm.getInventory().getAmount(Mockito.any(Crop.class))).thenReturn(0);
        builder = new FeedAnimalsMenuControllerBuilder(gameController, farm, stockyard);
        MenuController menuController = builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("FEED"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                    Mockito.verify(farm.getInventory(), Mockito.never()).removeItem(Mockito.any(), Mockito.anyInt());
                });
    }
}
