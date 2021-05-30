package controller.farm.element.building.edifice;

import controller.GameController;
import controller.GameControllerState;
import controller.command.Command;
import controller.command.controller_state.OpenPopupMenuCommand;
import controller.farm.FarmController;
import controller.menu.builder.building.HouseMenuControllerBuilder;
import model.farm.building.Edifice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HouseControllerTest {
    private HouseController houseController;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        gameController = Mockito.mock(GameController.class);
        houseController = new HouseController(gameController);
    }

    @Test
    public void getInteractionCommand() {
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(FarmController.class));
        Command command = houseController.getInteractionCommand(new Edifice("HOUSE"));
        Assertions.assertTrue(command instanceof OpenPopupMenuCommand);
        OpenPopupMenuCommand openPopupMenuCommand = (OpenPopupMenuCommand) command;
        Assertions.assertTrue(openPopupMenuCommand.getMenuBuilder() instanceof HouseMenuControllerBuilder);
    }

    @Test
    public void getInteractionCommandNotInFarm() {
        Mockito.when(gameController.getGameControllerState()).thenReturn(Mockito.mock(GameControllerState.class));
        Assertions.assertThrows(RuntimeException.class,
                () -> houseController.getInteractionCommand(new Edifice("HOUSE")));
    }
}
