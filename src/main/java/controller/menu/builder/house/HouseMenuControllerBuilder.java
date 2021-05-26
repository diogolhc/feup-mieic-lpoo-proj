package controller.menu.builder.house;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.command.*;
import controller.farm.FarmController;
import controller.farm.FarmSleepingController;
import controller.farm.FarmWithFarmerController;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.building.House;
import model.menu.Button;

import java.util.List;

public class HouseMenuControllerBuilder extends PopupMenuControllerBuilder {
    private House house;
    private RealTimeToInGameTimeConverter timeConverter;
    private FarmController farmController;

    public HouseMenuControllerBuilder(GameController controller, FarmController farmController,
                                      RealTimeToInGameTimeConverter timeConverter, House house) {
        super(controller);
        this.farmController = farmController;
        this.timeConverter = timeConverter;
        this.house = house;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button saveGame = new Button(new Position(1,5), "SAVE GAME");
        Command saveCommand = new CompoundCommand()
                .addCommand(new SaveGameCommand(this.farmController.getFarm()))
                .addCommand(new SetControllerStateCommand(this.controller, farmController));

        buttons.add(new ButtonController(saveGame, saveCommand));

        Button restButton = new Button(new Position(1, 9), "REST");
        Command restCommand = new CompoundCommand()
                .addCommand(new SetTimeRateCommand(this.timeConverter, this.house.getSleepRate()))
                .addCommand(new SetControllerStateCommand(this.controller, new FarmSleepingController(this.farmController)));

        buttons.add(new ButtonController(restButton, restCommand));
        return buttons;
    }

    @Override
    protected int getHeight() {
        return 13;
    }

    @Override
    protected int getWidth() {
        return 20;
    }

    @Override
    protected String getTitle() {
        return "HOUSE";
    }

}
