package controller.menu.builder.house;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.command.*;
import controller.farm.FarmController;
import controller.farm.FarmSleepingController;
import controller.menu.ButtonController;
import controller.menu.builder.PopupMenuControllerBuilder;
import model.Position;
import model.farm.building.House;
import model.menu.Button;

import java.util.List;

public class SleepMenuControllerBuilder extends PopupMenuControllerBuilder {
    private House house;
    private RealTimeToInGameTimeConverter timeConverter;
    private FarmController farmController;

    public SleepMenuControllerBuilder(GameController controller, FarmController farmController,
                                      RealTimeToInGameTimeConverter timeConverter, House house) {
        super(controller);
        this.farmController = farmController;
        this.timeConverter = timeConverter;
        this.house = house;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button sleepButton = new Button(new Position(1, 5), "SLEEP");
        Command sleepCommand = new CompoundCommand()
                .addCommand(new SetTimeRateCommand(this.timeConverter, this.house.getSleepRate()))
                .addCommand(new SetControllerStateCommand(this.controller, new FarmSleepingController(this.farmController)));

        buttons.add(new ButtonController(sleepButton, sleepCommand));
        return buttons;
    }

    @Override
    protected int getHeight() {
        return 10;
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
