package controller.menu.builder;

import controller.GameController;
import controller.GameControllerState;
import controller.command.*;
import controller.menu.ButtonController;
import controller.menu.PopupMenuController;
import model.Position;
import model.farm.building.House;
import model.menu.Button;

import java.util.ArrayList;
import java.util.List;

public class SleepMenuControllerBuilder extends PopupMenuControllerBuilder {
    private House house;

    public SleepMenuControllerBuilder(GameController controller, House house) {
        super(controller);
        this.house = house;
    }

    @Override
    protected List<ButtonController> getButtons() {
        List<ButtonController> buttons = super.getButtons();

        Button sleepButton = new Button(new Position(1, 5), "SLEEP");
        Command sleepCommand = new CompoundCommand()
                .addCommand(new SleepCommand(house, true))
                .addCommand(new SetControllerStateCommand(this.controller, this.getStopSleepState()));

        buttons.add(new ButtonController(sleepButton, sleepCommand));
        return buttons;
    }

    private GameControllerState getStopSleepState() {
        PopupMenuControllerBuilder popupMenuControllerBuilder;
        popupMenuControllerBuilder = new StopSleepMenuControllerBuilder(this.controller, house);
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
