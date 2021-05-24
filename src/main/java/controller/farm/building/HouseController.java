package controller.farm.building;

import controller.GameController;
import controller.RealTimeToInGameTimeConverter;
import controller.command.Command;
import controller.command.OpenPopupMenuCommand;
import controller.command.SetControllerStateCommand;
import controller.menu.builder.AlertMenuControllerBuilder;
import controller.menu.builder.PopupMenuControllerBuilder;
import controller.menu.builder.house.SleepMenuControllerBuilder;
import controller.menu.builder.MenuControllerBuilder;
import model.Position;
import model.farm.building.House;

public class HouseController extends BuildingController<House> {
    private final GameController controller;
    private RealTimeToInGameTimeConverter timeConverter;

    public HouseController(GameController controller, RealTimeToInGameTimeConverter timeConverter) {
        this.controller = controller;
        this.timeConverter = timeConverter;
    }

    @Override
    public Command getInteractionCommand(House house) {
        PopupMenuControllerBuilder menuControllerBuilder;
        menuControllerBuilder = new SleepMenuControllerBuilder(this.controller, timeConverter, house);

        return new OpenPopupMenuCommand(this.controller, menuControllerBuilder);
    }

    @Override
    public Command getDemolishCommand(House house) {
        return new OpenPopupMenuCommand(this.controller, new AlertMenuControllerBuilder(this.controller,
                "HOUSE CANNOT BE DEMOLISHED"));
    }
}
