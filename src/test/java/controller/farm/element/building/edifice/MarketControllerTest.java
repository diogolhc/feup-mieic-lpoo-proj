package controller.farm.element.building.edifice;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.building.market.MarketMenuControllerBuilder;
import model.farm.Farm;
import model.farm.building.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MarketControllerTest {
    private MarketController marketController;
    private GameController gameController;
    private Farm farm;

    @BeforeEach
    public void setUp() {
        gameController = Mockito.mock(GameController.class);
        farm = Mockito.mock(Farm.class);
        marketController = new MarketController(gameController, farm);
    }

    @Test
    public void getInteractionCommand() {
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(FarmController.class));
        Command command = marketController.getInteractionCommand(new Edifice("MARKET"));
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof MarketMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandNotInFarm() {
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> marketController.getInteractionCommand(new Edifice("MARKET")));
    }
}
