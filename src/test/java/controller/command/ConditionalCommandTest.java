package controller.command;

import controller.GameController;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.command.farm.stockyard.SellAnimalCommand;
import controller.menu.MenuController;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.info.AlertMenuControllerBuilder;
import gui.GUI;
import model.Position;
import model.farm.Currency;
import model.farm.Wallet;
import model.farm.building.stockyard.Stockyard;
import model.farm.building.stockyard.state.NotProducing;
import model.farm.data.Livestock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class ConditionalCommandTest {
    private Wallet wallet;
    private Stockyard stockyard;
    private NotProducing stateNotProducing;
    private ConditionalCommand command;
    private Livestock livestock;
    private GameController controller;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        gui = Mockito.mock(GUI.class);
        controller = Mockito.mock(GameController.class);

        stateNotProducing = Mockito.mock(NotProducing.class);
        wallet = new Wallet(new Currency(100));

        livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getMaxNumAnimals()).thenReturn(5);

        stockyard = new Stockyard(new Position(0, 0), livestock);
        stockyard.setState(stateNotProducing);
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();
        stockyard.getAnimals().addAnimal();

        Mockito.when(stockyard.getLivestockType().getAnimalSellPrice()).thenReturn(new Currency(10));
        PopupMenuControllerBuilder stockyardEmptyAlert = new AlertMenuControllerBuilder(this.controller,
                "STOCKYARD IS EMPTY");

        command = new ConditionalCommand(stockyard.getAnimals()::isEmpty);
        command.ifTrue(new OpenPopupMenuCommand(controller, stockyardEmptyAlert))
                .ifFalse(new SellAnimalCommand(wallet, stockyard.getAnimals(), new Currency(10)));

    }

    @Test
    public void executeTrue() {
        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(100, wallet.getCurrency().getCoins());

        command.execute();
        command.execute();
        command.execute();

        Assertions.assertSame(stateNotProducing, stockyard.getState());

        Assertions.assertEquals(0, stockyard.getAnimals().getSize());
        Assertions.assertEquals(130, wallet.getCurrency().getCoins());
    }

    @Test
    public void executeFalse() {
        Assertions.assertEquals(3, stockyard.getAnimals().getSize());
        Assertions.assertEquals(100, wallet.getCurrency().getCoins());

        command.execute();
        command.execute();
        command.execute();
        command.execute();

        Assertions.assertSame(stateNotProducing, stockyard.getState());
        Assertions.assertEquals(130, wallet.getCurrency().getCoins());

        Assertions.assertEquals(0, stockyard.getAnimals().getSize());
    }
}
