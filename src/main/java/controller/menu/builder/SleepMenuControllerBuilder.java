package controller.menu.builder;

import controller.GameController;
import controller.GameControllerState;
import controller.RealTimeToInGameTimeConverter;
import controller.command.*;
import controller.menu.ButtonController;
import model.Position;
import model.farm.building.House;
import model.menu.Button;

import java.util.List;

public class SleepMenuControllerBuilder extends PopupMenuControllerBuilder {
    private House house;
    private RealTimeToInGameTimeConverter timeConverter;

    public SleepMenuControllerBuilder(GameController controller, RealTimeToInGameTimeConverter timeConverter,
                                      House house) {
        super(controller);
        this.timeConverter = timeConverter;
        this.house = house;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button sleepButton = new Button(new Position(1, 5), "SLEEP");
        Command sleepCommand = new CompoundCommand()
                .addCommand(new SetTimeRateCommand(this.timeConverter, this.house.getSleepRate()))
                .addCommand(new SetControllerStateCommand(this.controller, this.getStopSleepState()));

        buttons.add(new ButtonController(sleepButton, sleepCommand));
        return buttons;
    }

    private GameControllerState getStopSleepState() {
        PopupMenuControllerBuilder popupMenuControllerBuilder;
        popupMenuControllerBuilder = new StopSleepMenuControllerBuilder(this.controller, this.timeConverter);
        return popupMenuControllerBuilder.buildMenu(new Position(1,1));
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
