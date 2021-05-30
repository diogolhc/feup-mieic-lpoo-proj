package controller.menu.builder.building.market;

import controller.GameController;
import controller.GameControllerState;
import controller.farm.FarmController;
import controller.farm.FarmNewBuildingController;
import controller.menu.MenuController;
import controller.menu.PopupMenuController;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.Currency;
import model.farm.Farm;
import model.farm.Wallet;
import model.farm.building.crop_field.CropField;
import model.farm.data.Livestock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;

public class BuildMenuControllerBuilderTest {
    private MenuControllerBuilder builder;
    private FarmController farmController;
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        this.gameController = Mockito.mock(GameController.class);
        this.farmController = Mockito.mock(FarmController.class);
        Mockito.when(this.farmController.getFarm()).thenReturn(Mockito.mock(Farm.class));
        Mockito.when(this.farmController.getFarm().getWallet()).thenReturn(new Wallet(new Currency(10)));
        Livestock livestock = Mockito.mock(Livestock.class);
        Mockito.when(livestock.getStockyardWidth()).thenReturn(4);
        Mockito.when(livestock.getStockyardHeight()).thenReturn(4);
        Mockito.when(livestock.getAnimalName()).thenReturn("ANIMAL");
        Mockito.when(livestock.getBuildPrice()).thenReturn(new Currency(5));
        Mockito.when(this.farmController.getFarm().getLivestockTypes()).thenReturn(Arrays.asList(livestock));
        this.builder = new BuildMenuControllerBuilder(this.gameController, this.farmController);
    }

    @Test
    public void buildMenu() {
        MenuController menuController = this.builder.buildMenu();

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("CROPFIELD")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("ANIMAL S.Y.")));

        Assertions.assertTrue(menuController.getMenu().getButtons().stream().anyMatch(button ->
                button.getTitle().equals("DEMOLISH")));

        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals(new CropField(new Position(0, 0)).getBuildPrice().toString())));
        Assertions.assertTrue(menuController.getMenu().getLabels().stream().anyMatch(label ->
                label.getString().equals("5C")));
    }

    @Test
    public void canAffordBuildable() {
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("ANIMAL S.Y."))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof FarmNewBuildingController);
                });
    }

    @Test
    public void cannotAffordBuildable() {
        MenuController menuController = this.builder.buildMenu();

        menuController
                .getButtons()
                .stream()
                .filter(buttonController -> buttonController.getButton().getTitle().equals("CROPFIELD"))
                .forEach(buttonController -> {
                    buttonController.getCommand().execute();
                    ArgumentCaptor<GameControllerState> captor = ArgumentCaptor.forClass(GameControllerState.class);
                    Mockito.verify(this.gameController, Mockito.times(1)).setGameControllerState(captor.capture());
                    Assertions.assertTrue(captor.getValue() instanceof PopupMenuController);
                });
    }
}
